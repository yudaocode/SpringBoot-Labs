package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo11Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Demo11Producer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void syncSend(Integer id) throws InterruptedException {
        // 创建 Demo11Message 消息
        Demo11Message message = new Demo11Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo11Message.EXCHANGE, Demo11Message.ROUTING_KEY, message);
        logger.info("[syncSend][发送编号：[{}] 发送成功]", id);

        // 等待
        Thread.sleep(10 * 1000L);
    }

}
