package cn.iocoder.springboot.lab40.zipkin.config;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.opentracing.BraveTracer;
import brave.servlet.TracingFilter;
import com.mongodb.MongoClientOptions;
import io.opentracing.Tracer;
import io.opentracing.contrib.mongo.common.TracingCommandListener;
import org.springframework.beans.factory.annotation.Value;
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

    // ==================== MongoDB 相关 ====================

    @Bean
    public MongoClientOptions mongoClientOptions(Tracer tracer) {
        // 创建 TracingCommandListener 对象
        TracingCommandListener listener = new TracingCommandListener.Builder(tracer).build();
        // 创建 MongoClientOptions 对象，并设置监听器
        return MongoClientOptions.builder().addCommandListener(listener).build();
    }

}
