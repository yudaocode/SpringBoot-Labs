package cn.iocoder.springboot.lab58.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        // 设置端口
        System.setProperty("server.port", "18080");

        // 应用启动
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
