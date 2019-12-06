package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo01Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
    }

//    public void asyncSend(Integer id, SendCallback callback) {
//        // 创建 Demo01Message 消息
//        Demo01Message message = new Demo01Message();
//        message.setId(id);
//        // 异步发送消息
//        rocketMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
//    }
//
//    public void onewaySend(Integer id) {
//        // 创建 Demo01Message 消息
//        Demo01Message message = new Demo01Message();
//        message.setId(id);
//        // oneway 发送消息
//        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC, message);
//    }

}
