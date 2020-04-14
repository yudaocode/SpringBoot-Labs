package cn.iocoder.springcloud.labx13.springmvcdemo.config;

import cn.iocoder.springcloud.labx13.springmvcdemo.spring.TracingTransportClientFactoryBean;
import io.opentracing.Tracer;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SleuthConfiguration {

    // ==================== Elasticsearch 相关 ====================

    @Bean
    public TransportClient elasticsearchClient(Tracer tracer, ElasticsearchProperties elasticsearchProperties) throws Exception {
        // 创建 TracingTransportClientFactoryBean 对象
        TracingTransportClientFactoryBean factory = new TracingTransportClientFactoryBean(tracer);
        // 设置其属性
        factory.setClusterNodes(elasticsearchProperties.getClusterNodes());
        factory.setProperties(this.createElasticsearch(elasticsearchProperties));
        // 创建 TransportClient 对象，并返回
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    private Properties createElasticsearch(ElasticsearchProperties elasticsearchProperties) {
        Properties properties = new Properties();
        properties.put("cluster.name", elasticsearchProperties.getClusterName());
        properties.putAll(elasticsearchProperties.getProperties());
        return properties;
    }
}
