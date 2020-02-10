package cn.iocoder.springcloud.labx03.feigndemo.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignClientConfiguration {

    @Bean
    public Logger.Level defaultFeignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
