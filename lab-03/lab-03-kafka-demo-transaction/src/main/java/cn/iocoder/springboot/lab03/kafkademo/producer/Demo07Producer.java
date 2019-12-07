package cn.iocoder.springboot.lab03.kafkademo.producer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo07Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo07Producer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建 Demo07Message 消息
        Demo07Message message = new Demo07Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo07Message.TOPIC, message).get();
    }

    public String syncSendInTransaction(Integer id, Runnable runner) throws ExecutionException, InterruptedException {
        return kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<Object, Object, String>() {

            @Override
            public String doInOperations(KafkaOperations<Object, Object> kafkaOperations) {
                // 创建 Demo07Message 消息
                Demo07Message message = new Demo07Message();
                message.setId(id);
                try {
                    SendResult<Object, Object> sendResult = kafkaOperations.send(Demo07Message.TOPIC, message).get();
                    logger.info("[doInOperations][发送编号：[{}] 发送结果：[{}]]", id, sendResult);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // 本地业务逻辑... biubiubiu
                runner.run();

                // 返回结果
                return "success";
            }

        });
    }

}
