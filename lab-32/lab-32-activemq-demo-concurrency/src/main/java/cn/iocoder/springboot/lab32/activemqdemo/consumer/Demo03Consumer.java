package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo03Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Demo03Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = Demo03Message.QUEUE,
        concurrency = "10")
    public void onMessage(Demo03Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
