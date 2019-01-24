package cn.iocoder.springboot.labs.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

//        KafkaSender sender = context.getBean(KafkaSender.class);
//
//        for (int i = 0; i < 3; i++) {
//            //调用消息发送类中的消息发送方法
//            sender.send();
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
