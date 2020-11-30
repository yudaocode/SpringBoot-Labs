package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启事务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
