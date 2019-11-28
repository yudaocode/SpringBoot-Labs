package cn.iocoder.springboot.lab28.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application02 {

    public static void main(String[] args) {
        // 设置 Tomcat 随机端口
        System.setProperty("server.port", "0");

        // 启动 Spring Boot 应用
        SpringApplication.run(Application.class, args);
    }

}
