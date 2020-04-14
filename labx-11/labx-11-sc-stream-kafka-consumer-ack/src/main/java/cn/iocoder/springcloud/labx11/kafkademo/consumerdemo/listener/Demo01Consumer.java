package cn.iocoder.springcloud.labx11.kafkademo.consumerdemo.listener;

import cn.iocoder.springcloud.labx11.kafkademo.consumerdemo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger index = new AtomicInteger();

    @StreamListener(MySink.DEMO01_INPUT)
    public void onMessage(@Payload Demo01Message message,
                          @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment acknowledgment) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 提交消费进度
//        if (message.getId() % 2 == 1) {
        if (index.incrementAndGet() == 1) {
            acknowledgment.acknowledge();
        }
    }

}
