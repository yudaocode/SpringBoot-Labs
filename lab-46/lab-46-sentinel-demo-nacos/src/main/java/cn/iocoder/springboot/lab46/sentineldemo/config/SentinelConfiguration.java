package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.boot.nacos.config.properties.NacosConfigProperties;
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
    public NacosDataSource nacosDataSource(NacosConfigProperties nacosConfigProperties, ObjectMapper objectMapper) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
        NacosDataSource<List<FlowRule>> nacosDataSource = new NacosDataSource<>(properties,
                nacosConfigProperties.getGroup(), nacosConfigProperties.getDataId(),
                new Converter<String, List<FlowRule>>() {
                    @Override
                    public List<FlowRule> convert(String value) {
                        try {
                            return Arrays.asList(objectMapper.readValue(value, FlowRule[].class));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        FlowRuleManager.register2Property(nacosDataSource.getProperty());
        return nacosDataSource;
    }

}
