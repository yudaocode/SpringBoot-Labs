package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * TODO RabbitMQ 顺序消息的示例，暂时未提供
 */
@SpringBootApplication
@EnableAsync // 开启异步
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
