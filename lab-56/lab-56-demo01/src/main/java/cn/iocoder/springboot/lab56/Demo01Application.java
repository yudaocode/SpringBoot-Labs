package cn.iocoder.springboot.lab56;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo01Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "18080"); // 端口 18080
        SpringApplication.run(Demo01Application.class, args);
    }

}
