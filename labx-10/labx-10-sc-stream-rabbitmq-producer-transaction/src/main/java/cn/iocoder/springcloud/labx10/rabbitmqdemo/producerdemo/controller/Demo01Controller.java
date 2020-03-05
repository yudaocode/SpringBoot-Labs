package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.controller;

import cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message.Demo01Message;
import cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message.MySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MySource mySource;

    @Transactional
    @GetMapping("/send_transaction")
    public void sendTransaction() throws InterruptedException {
        // 创建 Message
        int id = new Random().nextInt();
        Demo01Message message = new Demo01Message()
                .setId(id);
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        // 发送消息
        mySource.demo01Output().send(springMessage);
        logger.info("[syncSend][发送编号：[{}] 发送成功]", id);

        // <X> 等待
        Thread.sleep(10 * 1000L);
    }

}
