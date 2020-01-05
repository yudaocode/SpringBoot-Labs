package cn.iocoder.springboot.lab39.skywalkingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true) // 开启 @Async 的支持
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

}
