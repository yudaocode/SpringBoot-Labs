package cn.iocoder.springcloud.labx11.kafkademo.kafkademo.config;

import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.MessageChannel;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(BinderFactory binders) {
        // 获得 Kafka ProducerFactory 对象
        ProducerFactory<byte[], byte[]> pf = ((KafkaMessageChannelBinder) binders.getBinder(null,
                MessageChannel.class)).getTransactionalProducerFactory();
        // 创建 KafkaTransactionManager 事务管理器
        assert pf != null;
        return new KafkaTransactionManager<>(pf);
    }

}
