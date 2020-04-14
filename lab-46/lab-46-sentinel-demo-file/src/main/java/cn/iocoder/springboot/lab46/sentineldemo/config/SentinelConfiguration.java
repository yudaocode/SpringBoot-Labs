package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SentinelConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

//    @Bean
    public FileRefreshableDataSource<List<FlowRule>> refreshableDataSource(ObjectMapper objectMapper) throws IOException {
        // File 配置。这里先写死，推荐后面写到 application.yaml 配置文件中。
        ClassPathResource resource = new ClassPathResource("/flow-rule.json");

        // 创建 FileRefreshableDataSource 对象
        FileRefreshableDataSource<List<FlowRule>> refreshableDataSource = new FileRefreshableDataSource<>(resource.getFile(),
                new Converter<String, List<FlowRule>>() { // 转换器，将读取的文本配置，转换成 FlowRule 数组
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
        FlowRuleManager.register2Property(refreshableDataSource.getProperty());
        return refreshableDataSource;
    }

    @Bean
    public FileWritableDataSource writableDataSource(ObjectMapper objectMapper) throws IOException {
        // File 配置。这里先写死，推荐后面写到 application.yaml 配置文件中。
        String directory = System.getProperty("user.home") + File.separator
                + "sentinel" + File.separator
                + System.getProperty("project.name");
        mkdirs(directory);
        String path = directory + File.separator + "flow-rule.json";
        creteFile(path);

        // 创建 FileRefreshableDataSource 对象
        FileRefreshableDataSource<List<FlowRule>> refreshableDataSource = new FileRefreshableDataSource<>(path,
                new Converter<String, List<FlowRule>>() { // 转换器，将读取的文本配置，转换成 FlowRule 数组
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
        FlowRuleManager.register2Property(refreshableDataSource.getProperty());

        // 创建 FileWritableDataSource 对象
        FileWritableDataSource<List<FlowRule>> fileWritableDataSource = new FileWritableDataSource<>(path,
                new Converter<List<FlowRule>, String>() {
                    @Override
                    public String convert(List<FlowRule> source) {
                        try {
                            return objectMapper.writeValueAsString(source);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        // 注册到 WritableDataSourceRegistry 中
        WritableDataSourceRegistry.registerFlowDataSource(fileWritableDataSource);
        return fileWritableDataSource;
    }

    private void mkdirs(String path) {
        File file = new File(path);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private void creteFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return;
        }
        file.createNewFile();
    }

}
