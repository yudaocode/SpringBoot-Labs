package cn.iocoder.springcloudalibaba.labx20.listenerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

@SpringBootApplication
@RemoteApplicationEventScan
public class ListenerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerDemoApplication.class, args);
    }

}
