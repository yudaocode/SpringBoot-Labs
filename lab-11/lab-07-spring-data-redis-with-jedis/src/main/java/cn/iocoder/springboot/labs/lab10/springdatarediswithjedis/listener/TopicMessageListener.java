package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class TopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("收到消息：");
        System.out.println("message：" + message);
        System.out.println("pattern：" + new String(pattern));
    }

}
