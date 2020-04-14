package cn.iocoder.springboot.lab39.skywalkingdemo.rocketmqdemo.producer;

import cn.iocoder.springboot.lab39.skywalkingdemo.rocketmqdemo.message.DemoMessage;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id) {
        // 创建 DemoMessage 消息
        DemoMessage message = new DemoMessage();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(DemoMessage.TOPIC, message);
    }

}
