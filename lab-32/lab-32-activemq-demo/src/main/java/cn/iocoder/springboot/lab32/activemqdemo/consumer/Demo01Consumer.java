package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = Demo01Message.QUEUE)
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

//    @JmsListener(destination = Demo01Message.QUEUE)
//    public void onMessage(javax.jms.Message message) {
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//    }

}
