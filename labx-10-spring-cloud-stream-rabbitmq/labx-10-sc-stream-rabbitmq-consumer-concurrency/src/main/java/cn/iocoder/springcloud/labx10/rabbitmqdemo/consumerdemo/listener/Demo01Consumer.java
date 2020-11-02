package cn.iocoder.springcloud.labx10.rabbitmqdemo.consumerdemo.listener;

import cn.iocoder.springcloud.labx10.rabbitmqdemo.consumerdemo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(MySink.DEMO01_INPUT)
    public void onMessage(@Payload Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//
//        try {
//            Thread.sleep(10 * 1000L);
//        } catch (InterruptedException ignored) {
//        }
    }

}
