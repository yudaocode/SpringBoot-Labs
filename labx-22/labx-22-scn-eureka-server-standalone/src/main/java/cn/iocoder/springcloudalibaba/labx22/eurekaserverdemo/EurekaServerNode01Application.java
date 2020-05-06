package cn.iocoder.springcloudalibaba.labx22.eurekaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerNode01Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "node01");
        SpringApplication.run(EurekaServerNode01Application.class,args);
    }

}
