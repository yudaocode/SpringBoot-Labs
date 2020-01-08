package cn.iocoder.springboot.lab40.zipkindemo.consumer;

import cn.iocoder.springboot.lab40.zipkindemo.message.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = DemoMessage.TOPIC,
            groupId = "demo-consumer-group-" + DemoMessage.TOPIC)
    public void onMessage(DemoMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
