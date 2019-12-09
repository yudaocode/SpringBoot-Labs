package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo02Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo02Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String routingKey) {
        // 创建 Demo02Message 消息
        Demo02Message message = new Demo02Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, routingKey, message);
    }

}
