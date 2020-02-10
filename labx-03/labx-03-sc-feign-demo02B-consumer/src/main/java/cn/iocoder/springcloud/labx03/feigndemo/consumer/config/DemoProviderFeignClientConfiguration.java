package cn.iocoder.springcloud.labx03.feigndemo.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DemoProviderFeignClientConfiguration {

    @Bean
    @Primary
    public Logger.Level feignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
