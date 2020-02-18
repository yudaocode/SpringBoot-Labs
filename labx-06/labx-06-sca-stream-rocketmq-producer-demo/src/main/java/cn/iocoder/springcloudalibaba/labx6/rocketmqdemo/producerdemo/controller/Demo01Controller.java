package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.controller;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.Demo01Message;
import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    @Autowired
    private MySource mySource;

    @GetMapping("/send")
    public boolean send() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        // 发送消息
        return mySource.demo01Output().send(springMessage);
    }

}
