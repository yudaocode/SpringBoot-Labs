package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo14ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo14Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        String result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功 消费结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

//    @Test
//    public void testSyncSend2() throws InterruptedException {
//        int id = (int) (System.currentTimeMillis() / 1000);
//        String result = producer.syncSend(id);
//        logger.info("[testSyncSend][发送编号：[{}] 发送成功 消费结果：[{}]]", id, result);
//
//        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
//    }

}
