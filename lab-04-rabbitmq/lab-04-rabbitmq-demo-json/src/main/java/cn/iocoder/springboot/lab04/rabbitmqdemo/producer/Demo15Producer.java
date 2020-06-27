package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo15Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo15Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo01Message 消息
        Demo15Message message = new Demo15Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo15Message.EXCHANGE, Demo15Message.ROUTING_KEY, message);
    }

}
