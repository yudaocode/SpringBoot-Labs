package cn.iocoder.springboot.lab31.rocketmqdemo.consumer;

import cn.iocoder.springboot.lab31.rocketmqdemo.message.Demo02Message;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
//@RocketMQMessageListener(
//        topic = Demo02Message.TOPIC,
//        consumerGroup = "demo02-consumer-group-" + Demo02Message.TOPIC
//)
public class Demo02Consumer implements RocketMQListener<Demo02Message> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Demo02Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
