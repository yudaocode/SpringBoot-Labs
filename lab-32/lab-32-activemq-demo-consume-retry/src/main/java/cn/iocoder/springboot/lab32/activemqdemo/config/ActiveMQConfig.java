package cn.iocoder.springboot.lab32.activemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig implements ActiveMQConnectionFactoryCustomizer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void customize(ActiveMQConnectionFactory factory) {
        logger.info("[customize][默认重试策略: {}]", factory.getRedeliveryPolicy());
    }

}
