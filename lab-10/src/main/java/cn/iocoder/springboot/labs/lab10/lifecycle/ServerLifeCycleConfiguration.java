package cn.iocoder.springboot.labs.lab10.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerLifeCycleConfiguration {

    @Bean
    public ServerLifeCycleHealthIndicator serverLifeCycleHealthIndicator() {
        return new ServerLifeCycleHealthIndicator();
    }

    @Bean
    public ServerLifeCycleListener serverLifeCycleListener() {
        return new ServerLifeCycleListener(this.serverLifeCycleHealthIndicator());
    }

}
