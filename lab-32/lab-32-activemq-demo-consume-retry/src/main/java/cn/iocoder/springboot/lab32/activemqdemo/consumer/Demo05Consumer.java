package cn.iocoder.springboot.lab32.activemqdemo.consumer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo05Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Demo05Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = Demo05Message.QUEUE)
    public void onMessage(Demo05Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我就是故意抛出一个异常");
    }

}
