package cn.iocoder.springboot.lab04.rabbitmqdemo.consumer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.ClusteringMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(
                        name = ClusteringMessage.QUEUE + "-" + "GROUP-01"
                ),
                exchange = @Exchange(
                        name = ClusteringMessage.EXCHANGE,
                        type = ExchangeTypes.TOPIC,
                        declare = "false"
                ),
                key = "#"
        )
)
public class ClusteringConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ClusteringMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
