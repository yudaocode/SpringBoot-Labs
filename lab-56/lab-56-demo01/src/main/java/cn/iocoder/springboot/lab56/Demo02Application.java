package cn.iocoder.springboot.lab56;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo02Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "28080");  // 端口 28080
        SpringApplication.run(Demo02Application.class, args);
    }

}
