package cn.iocoder.springcloud.labx13.kafkademo.consumerdemo;

import cn.iocoder.springcloud.labx13.kafkademo.consumerdemo.listener.MySink;
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
