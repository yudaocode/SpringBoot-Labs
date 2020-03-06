package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.controller;

import cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message.Demo01Message;
import cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message.MySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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

    @GetMapping("/send")
    public boolean send() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        // 发送消息
        return mySource.demo01Output().send(springMessage);
    }

//    @StreamListener(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME) // errorChannel
//    public void globalHandleError(ErrorMessage errorMessage) {
//        logger.error("[globalHandleError][payload：{}]", errorMessage.getPayload().getMessage());
//        logger.error("[globalHandleError][originalMessage：{}]", errorMessage.getOriginalMessage());
//        logger.error("[globalHandleError][headers：{}]", errorMessage.getHeaders());
//    }

//    @StreamListener("ooxx") // errorChannel
//    public void ooxx(ErrorMessage errorMessage) {
//        logger.error("[globalHandleError][payload：{}]", errorMessage.getPayload().getMessage());
//        logger.error("[globalHandleError][originalMessage：{}]", errorMessage.getOriginalMessage());
//        logger.error("[globalHandleError][headers：{}]", errorMessage.getHeaders());
//    }

////    @ServiceActivator(inputChannel = "demo-producer-application.ooxx")
//    @ServiceActivator(inputChannel = "demo-producer-application.ooxx")
////    @StreamListener("demo-producer-application.ooxx") // errorChannel
//    public void handleError(Message<?> errorMessage) {
////        logger.error("[handleError][payload：{}]", errorMessage.getPayload().getMessage());
////        logger.error("[handleError][originalMessage：{}]", errorMessage.getOriginalMessage());
////        logger.error("[handleError][headers：{}]", errorMessage.getHeaders());
//        System.out.println();
//    }

    @ServiceActivator(inputChannel = "publisher-confirm")
    public void onPublisherConfirm(Message message) {
        logger.debug("on publisher confirm");
    }

}
