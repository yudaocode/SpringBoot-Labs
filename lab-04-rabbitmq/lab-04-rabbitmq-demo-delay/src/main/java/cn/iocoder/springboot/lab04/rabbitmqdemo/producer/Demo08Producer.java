package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo08Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo08Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, Integer delay) {
        // 创建 Demo07Message 消息
        Demo08Message message = new Demo08Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, message, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息的 TTL 过期时间
                if (delay != null && delay > 0) {
                    message.getMessageProperties().setExpiration(String.valueOf(delay)); // Spring-AMQP API 设计有问题，所以传入了 String = =
                }
                return message;
            }

        });
    }

}
