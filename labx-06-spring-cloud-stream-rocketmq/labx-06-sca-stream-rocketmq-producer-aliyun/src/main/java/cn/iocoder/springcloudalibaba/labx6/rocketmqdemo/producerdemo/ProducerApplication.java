package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MySource.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
