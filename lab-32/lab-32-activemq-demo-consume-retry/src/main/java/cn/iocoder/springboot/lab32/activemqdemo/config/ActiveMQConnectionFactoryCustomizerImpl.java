package cn.iocoder.springboot.lab32.activemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConnectionFactoryCustomizerImpl implements ActiveMQConnectionFactoryCustomizer {

    @Override
    public void customize(ActiveMQConnectionFactory factory) {
        System.out.println();
    }

}
