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

//    @Bean
//    public KafkaTransactionManager transactionManager(ProducerFactory<byte[], byte[]> producerFactory) {
//        return new KafkaTransactionManager<>(producerFactory);
//    }

    @Bean
    public PlatformTransactionManager transactionManager(BinderFactory binders) {
        ProducerFactory<byte[], byte[]> pf = ((KafkaMessageChannelBinder) binders.getBinder(null,
                MessageChannel.class)).getTransactionalProducerFactory();
        return new KafkaTransactionManager<>(pf);
    }



}
