package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.consumerdemo;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.consumerdemo.listener.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MySink.class)
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
