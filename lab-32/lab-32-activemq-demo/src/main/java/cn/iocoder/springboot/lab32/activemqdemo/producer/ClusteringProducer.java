package cn.iocoder.springboot.lab32.activemqdemo.producer;

import cn.iocoder.springboot.lab32.activemqdemo.config.RabbitConfig;
import cn.iocoder.springboot.lab32.activemqdemo.message.ClusteringMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ClusteringProducer {

    @Resource(name = RabbitConfig.CLUSTERING_JMS_TEMPLATE_BEAN_NAME)
    private JmsMessagingTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 ClusteringMessage 消息
        ClusteringMessage message = new ClusteringMessage();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(ClusteringMessage.QUEUE, message);
    }

}
