package cn.iocoder.springcloud.labx15.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo01Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "18081"); // 端口 18081
        SpringApplication.run(Demo01Application.class, args);
    }

}
