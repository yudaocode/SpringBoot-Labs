package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.config.ActiveMQConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.BroadcastMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BroadcastProducer {

    @Resource(name = ActiveMQConfig.BROADCAST_JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void syncSend(Integer id) {
        // 创建 BroadcastMessage 消息
        BroadcastMessage message = new BroadcastMessage();
        message.setId(id);
        // 同步发送消息
        jmsMessagingTemplate.convertAndSend(BroadcastMessage.TOPIC, message);
    }

}
