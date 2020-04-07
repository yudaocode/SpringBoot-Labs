package cn.iocoder.springcloud.labx18.publisherdemo;

import cn.iocoder.springcloud.labx18.publisherdemo.event.UserRegisterEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

@SpringBootApplication
@RemoteApplicationEventScan(basePackageClasses = UserRegisterEvent.class)
public class PublisherDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherDemoApplication.class, args);
    }

}
