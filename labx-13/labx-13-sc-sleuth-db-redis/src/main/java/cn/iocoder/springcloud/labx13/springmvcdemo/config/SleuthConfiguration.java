package cn.iocoder.springcloud.labx13.springmvcdemo.config;

import io.opentracing.Tracer;
import io.opentracing.contrib.redis.common.TracingConfiguration;
import io.opentracing.contrib.redis.spring.data.connection.TracingRedisConnectionFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class SleuthConfiguration {

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
