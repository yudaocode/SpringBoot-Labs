package cn.iocoder.springboot.lab58.feigndemo.config;

import cn.iocoder.springboot.lab58.feigndemo.feign.UserServiceFeignClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserServiceFeignClient userServiceFeignClient() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(UserServiceFeignClient.class, "http://127.0.0.1:18080"); // 目标地址
    }

}
