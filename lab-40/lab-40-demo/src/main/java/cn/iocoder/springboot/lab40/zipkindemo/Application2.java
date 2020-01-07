package cn.iocoder.springboot.lab40.zipkindemo;

import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class Application2 {

    public static void main(String[] args) {
        System.setProperty("spring.application.name", "demo-application-02");
        System.setProperty("server.port", "8079");
        SpringApplication.run(Application2.class, args);
    }

}
