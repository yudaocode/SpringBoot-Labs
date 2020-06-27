package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo10Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo10Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo10Message 消息
        Demo10Message message = new Demo10Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo10Message.EXCHANGE, this.getRoutingKey(id), message);
    }

    private String getRoutingKey(Integer id) {
        return String.valueOf(id % Demo10Message.QUEUE_COUNT);
    }

}
