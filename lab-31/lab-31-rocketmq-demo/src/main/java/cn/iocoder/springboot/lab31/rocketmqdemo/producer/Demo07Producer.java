package cn.iocoder.springboot.lab31.rocketmqdemo.producer;

import cn.iocoder.springboot.lab31.rocketmqdemo.message.Demo07Message;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Demo07Producer {

    private static final String TX_PRODUCER_GROUP = "demo07-producer-group";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public TransactionSendResult sendMessageInTransaction(Integer id) {
        // 创建 Demo07Message 消息
        Message<Demo07Message> message = MessageBuilder.withPayload(new Demo07Message().setId(id))
                .build();
        // 发送事务消息
        return rocketMQTemplate.sendMessageInTransaction(TX_PRODUCER_GROUP, Demo07Message.TOPIC, message,
                id);
    }

    @RocketMQTransactionListener(txProducerGroup = TX_PRODUCER_GROUP)
    public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

        private Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // ... local transaction process, return rollback, commit or unknown
            logger.info("[executeLocalTransaction][执行本地事务，消息：{} arg：{}]", msg, arg);
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            // ... check transaction status and return rollback, commit or unknown
            logger.info("[checkLocalTransaction][回查消息：{}]", msg);
            return RocketMQLocalTransactionState.COMMIT;
        }

    }

}
