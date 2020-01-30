package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
public class SentinelConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @Bean
    public NacosDataSource nacosDataSource(ObjectMapper objectMapper) {
        // Nacos 配置。这里先写死，推荐后面写到 application.yaml 配置文件中。
        String serverAddress = "127.0.0.1:8848"; // Nacos 服务器地址
        String namespace = ""; // Nacos 命名空间
        String dataId = "demo-application-flow-rule"; // Nacos 配置集编号
//        String dataId = "example-sentinel"; // Nacos 配置集编号
        String group = "DEFAULT_GROUP"; // Nacos 配置分组

        // 创建 NacosDataSource 对象
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverAddress);
        properties.setProperty(PropertyKeyConst.NAMESPACE, namespace);
        NacosDataSource<List<FlowRule>> nacosDataSource = new NacosDataSource<>(properties, group, dataId,
                new Converter<String, List<FlowRule>>() { // 转换器，将读取的 Nacos 配置，转换成 FlowRule 数组
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
        FlowRuleManager.register2Property(nacosDataSource.getProperty());
        return nacosDataSource;
    }

}
