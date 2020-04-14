package cn.iocoder.springcloud.labx08.gatewaydemo.config;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    @DubboTransported(protocol = "dubbo")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
