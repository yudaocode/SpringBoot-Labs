package cn.iocoder.springboot.lab40.zipkindemo.config;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.servlet.TracingFilter;
import brave.spring.rabbit.SpringRabbitTracing;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.servlet.Filter;

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

    // ==================== RabbitMQ 相关 ====================

    @Bean
    public SpringRabbitTracing springRabbitTracing(Tracing tracing) {
        return SpringRabbitTracing.newBuilder(tracing)
                .remoteServiceName("demo-mq-rabbit") // 远程 RabbitMQ 服务名，可自定义
                .build();
    }

    @Bean
    public BeanPostProcessor rabbitmqBeanPostProcessor(SpringRabbitTracing springRabbitTracing) {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // 如果是 RabbitTemplate ，针对 RabbitMQ Producer
                if (bean instanceof RabbitTemplate) {
                    return springRabbitTracing.decorateRabbitTemplate((RabbitTemplate) bean);
                }
                // 如果是 SimpleRabbitListenerContainerFactory ，针对 RabbitMQ Consumer
                if (bean instanceof SimpleRabbitListenerContainerFactory) {
                    return springRabbitTracing.decorateSimpleRabbitListenerContainerFactory((SimpleRabbitListenerContainerFactory) bean);
                }
                return bean;
            }

        };
    }

}
