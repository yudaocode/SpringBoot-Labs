package cn.iocoder.springcloud.labx24.resilience4jdemo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class Resilience4jConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> resilience4JCircuitBreakerFactoryCustomizer() {
        return new Customizer<Resilience4JCircuitBreakerFactory>() {

            @Override
            public void customize(Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory) {
                // 设置默认的配置
                resilience4JCircuitBreakerFactory.configureDefault(new Function<String, Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration>() {

                    @Override
                    public Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration apply(String id) {
                        // 创建 TimeLimiterConfig 对象
                        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.ofDefaults(); // 默认
                        // 创建 CircuitBreakerConfig 对象
                        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults(); // 默认
                        // 创建 Resilience4JCircuitBreakerConfiguration 对象
                        return new Resilience4JConfigBuilder(id)
                                .timeLimiterConfig(timeLimiterConfig)
                                .circuitBreakerConfig(circuitBreakerConfig)
                                .build();
                    }

                });
                // 设置编号为 "slow" 的自定义配置
                resilience4JCircuitBreakerFactory.configure(new Consumer<Resilience4JConfigBuilder>() {
                    @Override
                    public void accept(Resilience4JConfigBuilder resilience4JConfigBuilder) {
                        // 创建 TimeLimiterConfig 对象
                        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)) // 自定义
                                .build();
                        // 创建 CircuitBreakerConfig 对象
                        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom() // 自定义
                                .slidingWindow(5, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                                .build();
                        // 设置 Resilience4JCircuitBreakerConfiguration 对象
                        resilience4JConfigBuilder
                                .timeLimiterConfig(timeLimiterConfig)
                                .circuitBreakerConfig(circuitBreakerConfig);
                    }
                }, "slow");
            }

        };
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
