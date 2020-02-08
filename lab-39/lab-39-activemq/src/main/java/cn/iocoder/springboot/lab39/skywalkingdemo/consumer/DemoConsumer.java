package cn.iocoder.springboot.lab39.skywalkingdemo.consumer;

import cn.iocoder.springboot.lab39.skywalkingdemo.message.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = DemoMessage.QUEUE)
    public void onMessage(DemoMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
