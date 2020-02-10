package cn.iocoder.springcloud.labx03.feigndemo.consumer;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.config.DefaultFeignClientConfiguration;
import cn.iocoder.springcloud.labx03.feigndemo.consumer.feign.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

    @Configuration
    @EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class)
    public class OpenFeignConfiguration {

    }

    @RestController
    static class TestController {

        @Autowired
        private DemoProviderFeignClient demoProviderFeignClient;

        @GetMapping("/hello02")
        public String hello02(String name) {
            // 使用 Feign 调用接口
            String response = demoProviderFeignClient.echo(name);
            // 返回结果
            return "consumer:" + response;
        }

    }

}
