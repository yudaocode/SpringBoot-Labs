package cn.iocoder.springboot.lab67.nettyclientdemo.config;

import cn.iocoder.springboot.lab67.nettycommondemo.dispacher.MessageDispatcher;
import cn.iocoder.springboot.lab67.nettycommondemo.dispacher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyClientConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

}
