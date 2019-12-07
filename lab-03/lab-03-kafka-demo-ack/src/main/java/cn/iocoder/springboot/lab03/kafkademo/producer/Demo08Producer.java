package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo08Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo08Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo08Message 消息
        Demo08Message message = new Demo08Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo08Message.TOPIC, message).get();
    }
    
}
