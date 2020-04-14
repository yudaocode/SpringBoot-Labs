package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.listener;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.controller.Demo01Controller;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(txProducerGroup = "test")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 从消息 Header 中解析到 args 参数，并使用 JSON 反序列化
        Demo01Controller.Args args = JSON.parseObject(msg.getHeaders().get("args", String.class),
                Demo01Controller.Args.class);
        // ... local transaction process, return rollback, commit or unknown
        logger.info("[executeLocalTransaction][执行本地事务，消息：{} args：{}]", msg, args);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return rollback, commit or unknown
        logger.info("[checkLocalTransaction][回查消息：{}]", msg);
        return RocketMQLocalTransactionState.COMMIT;
    }

}
