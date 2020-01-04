package cn.iocoder.springboot.lab39.skywalkingdemo.rocketmqdemo.consumer;

import cn.iocoder.springboot.lab39.skywalkingdemo.rocketmqdemo.message.DemoMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = DemoMessage.TOPIC,
        consumerGroup = "demo-consumer-group-" + DemoMessage.TOPIC
)
public class DemoConsumer implements RocketMQListener<DemoMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(DemoMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
