package cn.iocoder.springboot.lab40.zipkindemo.config;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.opentracing.BraveTracer;
import brave.servlet.TracingFilter;
import io.opentracing.Tracer;
import io.opentracing.contrib.redis.common.TracingConfiguration;
import io.opentracing.contrib.redis.spring.data.connection.TracingRedisConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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

    // ==================== Redis 相关 ====================
    @Bean
    public RedisConnectionFactory redisConnectionFactory(Tracer tracer, RedisProperties redisProperties) {
        // 创建 JedisConnectionFactory 对象
        RedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        // 创建 TracingConfiguration 对象
        TracingConfiguration tracingConfiguration = new TracingConfiguration.Builder(tracer)
                // 设置拓展 Tag ，设置 Redis 服务器地址。因为默认情况下，不会在操作 Redis 链路的 Span 上记录 Redis 服务器的地址，所以这里需要设置。
                .extensionTag("Server Address", redisProperties.getHost() + ":" + redisProperties.getPort())
                .build();
        // 创建 TracingRedisConnectionFactory 对象
        return new TracingRedisConnectionFactory(connectionFactory, tracingConfiguration);
    }

}
