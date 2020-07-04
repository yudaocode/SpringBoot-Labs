package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo05Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo05Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo05Message 消息
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo05Message.TOPIC, message).get();
    }

}
