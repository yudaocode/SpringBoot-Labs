package cn.iocoder.springboot.lab40.zipkindemo.config;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.kafka.clients.KafkaTracing;
import brave.servlet.TracingFilter;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.servlet.Filter;
import java.util.Properties;

@Configuration
public class ZipkinConfiguration {

    // ==================== 通用配置 ====================

    /**
     * Configuration for how to send spans to Zipkin
     */
    @Bean
    public Sender sender() { // Sender 采用 HTTP 通信方式
        return OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
    }

    /**
     * Configuration for how to buffer spans into messages for Zipkin
     */
    @Bean
    public AsyncReporter<Span> spanReporter() { // 异步 Reporter
        return AsyncReporter.create(sender());
    }

    /**
     * Controls aspects of tracing such as the service name that shows up in the UI
     */
    @Bean
    public Tracing tracing(@Value("${spring.application.name}") String serviceName) {
        return Tracing.newBuilder()
                .localServiceName(serviceName) // 应用名
                .spanReporter(this.spanReporter()).build();
    }

    /**
     * Allows someone to add tags to a span if a trace is in progress
     */
    @Bean
    public SpanCustomizer spanCustomizer(Tracing tracing) {
        return CurrentSpanCustomizer.create(tracing);
    }

    // ==================== HTTP 相关 ====================

    /**
     * Decides how to name and tag spans. By default they are named the same as the http method
     */
    @Bean
    public HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }

    /**
     * Creates server spans for http requests
     */
    @Bean
    public Filter tracingFilter(HttpTracing httpTracing) { // 拦截请求，记录 HTTP 请求的链路信息
        return TracingFilter.create(httpTracing);
    }

    // ==================== SpringMVC 相关 ====================
    // @see SpringMvcConfiguration 类上的，@Import(SpanCustomizingAsyncHandlerInterceptor.class) 。因为 SpanCustomizingAsyncHandlerInterceptor 未提供 public 构造方法

    // ==================== Kafka 相关 ====================

    @Bean
    public KafkaTracing kafkaTracing(Tracing tracing) {
        return KafkaTracing.newBuilder(tracing)
                .remoteServiceName("demo-mq-kafka") // 远程 Kafka 服务名，可自定义
                .build();
    }

    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties, KafkaTracing kafkaTracing) {
        // 创建 DefaultKafkaProducerFactory 对象
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory(properties.buildProducerProperties()) {

            @Override
            public Producer createProducer() {
                // 创建默认的 Producer
                Producer<?, ?> producer = super.createProducer();
                // 创建可链路追踪的 Producer
                return kafkaTracing.producer(producer);
            }

        };

        // 设置事务前缀
        String transactionIdPrefix = properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }

        return factory;
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties, KafkaTracing kafkaTracing) {
        // 创建 DefaultKafkaConsumerFactory 对象
        return new DefaultKafkaConsumerFactory(properties.buildConsumerProperties()) {

            @Override
            public Consumer<?, ?> createConsumer(String groupId, String clientIdPrefix,  String clientIdSuffix) {
                return this.createConsumer(groupId, clientIdPrefix, clientIdSuffix, null);
            }

            @Override
            public Consumer<?, ?> createConsumer(String groupId, String clientIdPrefix, final String clientIdSuffixArg, Properties properties) {
                // 创建默认的 Consumer
                Consumer<?, ?> consumer = super.createConsumer(groupId, clientIdPrefix, clientIdSuffixArg, properties);
                // 创建可链路追踪的 Consumer
                return kafkaTracing.consumer(consumer);
            }

        };
    }

}
