package cn.iocoder.springboot.lab27.springwebflux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableReactiveElasticsearchRepositories // 开启响应式的 Elasticsearch 的 Repository 的自动化配置
public class ElasticsearchConfiguration {
}
