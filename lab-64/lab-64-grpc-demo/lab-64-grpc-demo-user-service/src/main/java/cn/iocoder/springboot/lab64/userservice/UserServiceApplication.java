package cn.iocoder.springboot.lab64.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        // 启动 Spring Boot 应用
        SpringApplication.run(UserServiceApplication.class, args);
        // 阻塞，避免应用退出
        new CountDownLatch(1).await();
    }

}
