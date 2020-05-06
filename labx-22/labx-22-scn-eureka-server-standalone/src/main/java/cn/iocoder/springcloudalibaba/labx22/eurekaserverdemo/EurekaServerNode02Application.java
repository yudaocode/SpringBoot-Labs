package cn.iocoder.springcloudalibaba.labx22.eurekaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerNode02Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "node02");
        SpringApplication.run(EurekaServerNode02Application.class,args);
    }

}
