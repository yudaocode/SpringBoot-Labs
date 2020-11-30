package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo12Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo12Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo12Message 消息
        Demo12Message message = new Demo12Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo12Message.EXCHANGE, Demo12Message.ROUTING_KEY, message);
    }

}
