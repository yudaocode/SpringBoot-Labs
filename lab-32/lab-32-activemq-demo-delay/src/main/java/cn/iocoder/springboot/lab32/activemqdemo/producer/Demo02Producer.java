package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo02Message;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Demo02Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void syncSend(Integer id, Integer delay) {
        // 创建 Demo02Message 消息
        Demo02Message message = new Demo02Message();
        message.setId(id);
        // 创建 Header
        Map<String, Object> headers = null;
        if (delay != null && delay > 0) {
            headers = new HashMap<>();
            headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        }
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo02Message.QUEUE, message, headers);
    }

}
