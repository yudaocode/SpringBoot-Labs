package cn.iocoder.springboot.lab04.rabbitmqdemo.consumer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo03Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo03Message.QUEUE_B)
public class Demo03ConsumerB {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(Demo03Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
