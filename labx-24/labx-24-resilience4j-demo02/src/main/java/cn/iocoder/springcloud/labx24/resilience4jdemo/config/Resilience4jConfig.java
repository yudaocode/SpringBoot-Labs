package cn.iocoder.springcloud.labx24.resilience4jdemo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                        .circuitBreakerConfig(CircuitBreakerConfig.custom().slidingWindow(5, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED).build())
                        .build());
    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(
//                id -> new Resilience4JConfigBuilder(id)
//                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                        .build());
//    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
//        return factory -> factory.configure(builder -> builder
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()),
//                "slow");
//    }

}
