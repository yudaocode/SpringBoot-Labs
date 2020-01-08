package cn.iocoder.springboot.lab40.zipkindemo.config;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.opentracing.BraveTracer;
import brave.servlet.TracingFilter;
import cn.iocoder.springboot.lab40.zipkindemo.spring.TracingTransportClientFactoryBean;
import io.opentracing.Tracer;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
    }

    /**
     * Configuration for how to buffer spans into messages for Zipkin
     */
    @Bean
    public AsyncReporter<Span> spanReporter() {
        return AsyncReporter.create(sender());
    }

    /**
     * Controls aspects of tracing such as the service name that shows up in the UI
     */
    @Bean
    public Tracing tracing(@Value("${spring.application.name}") String serviceName) {
        return Tracing.newBuilder()
                .localServiceName(serviceName)
                .spanReporter(spanReporter()).build();
    }

    @Bean
    public Tracer openTracer(Tracing tracing) {
        return BraveTracer.create(tracing);
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
    public Filter tracingFilter(HttpTracing httpTracing) {
        return TracingFilter.create(httpTracing);
    }

    // ==================== SpringMVC 相关 ====================
    // @see SpringMvcConfiguration 类上的，@Import(SpanCustomizingAsyncHandlerInterceptor.class)

    // ==================== Elasticsearch 相关 ====================

    @Bean
    public TransportClient elasticsearchClient(Tracer tracer, ElasticsearchProperties elasticsearchProperties) throws Exception {
        // 创建 TracingTransportClientFactoryBean 对象
        TracingTransportClientFactoryBean factory = new TracingTransportClientFactoryBean(tracer);
        // 设置其属性
        factory.setClusterNodes(elasticsearchProperties.getClusterNodes());
        factory.setProperties(this.createElasticsearch(elasticsearchProperties));
        // 创建 TransportClient 对象，并返回
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    private Properties createElasticsearch(ElasticsearchProperties elasticsearchProperties) {
        Properties properties = new Properties();
        properties.put("cluster.name", elasticsearchProperties.getClusterName());
        properties.putAll(elasticsearchProperties.getProperties());
        return properties;
    }

}
