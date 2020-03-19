package cn.iocoder.springcloud.labx13.springmvcdemo.config;

import com.mongodb.MongoClientOptions;
import io.opentracing.Tracer;
import io.opentracing.contrib.mongo.common.TracingCommandListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SleuthConfiguration {

    // ==================== MongoDB 相关 ====================

    @Bean
    public MongoClientOptions mongoClientOptions(Tracer tracer) {
        // 创建 TracingCommandListener 对象
        TracingCommandListener listener = new TracingCommandListener.Builder(tracer).build();
        // 创建 MongoClientOptions 对象，并设置监听器
        return MongoClientOptions.builder().addCommandListener(listener).build();
    }

}
