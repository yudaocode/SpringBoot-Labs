package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.message.Demo03Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Demo03Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo03Message 消息
        Demo03Message message = new Demo03Message();
        message.setId(id);
        // 同步发送消息
        jmsTemplate.convertAndSend(Demo03Message.QUEUE, message);
    }

}
