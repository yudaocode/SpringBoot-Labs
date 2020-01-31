package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SentinelConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @Bean
    public ApolloDataSource apolloDataSource(ObjectMapper objectMapper) {
        // Apollo 配置。这里先写死，推荐后面写到 application.yaml 配置文件中。
        String appId = applicationName; // Apollo 项目编号。一般情况下，推荐和 spring.application.name 保持一致
        String serverAddress = "http://localhost:8080"; // Apollo Meta 服务器地址
        String namespace = "application"; // Apollo 命名空间
        String flowRuleKey = "sentinel.flow-rule"; // Apollo 配置项的 KEY

        // 创建 ApolloDataSource 对象
        System.setProperty("app.id", appId);
        System.setProperty("apollo.meta", serverAddress);
        ApolloDataSource<List<FlowRule>> apolloDataSource = new ApolloDataSource<>(namespace, flowRuleKey, "",
                new Converter<String, List<FlowRule>>() { // 转换器，将读取的 Apollo 配置，转换成 FlowRule 数组
                    @Override
                    public List<FlowRule> convert(String value) {
                        try {
                            return Arrays.asList(objectMapper.readValue(value, FlowRule[].class));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        // 注册到 FlowRuleManager 中
        FlowRuleManager.register2Property(apolloDataSource.getProperty());
        return apolloDataSource;
    }

}
