package cn.iocoder.springcloud.labx11.kafkademo.consumerdemo;

import cn.iocoder.springcloud.labx11.kafkademo.consumerdemo.listener.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MySink.class)
public class ConsumerApplication {

//    @Bean
//    public ListenerContainerCustomizer<AbstractMessageListenerContainer<byte[], byte[]>> customizer() {
//        return (container, dest, group) -> {
//            KafkaTransactionManager<?, ?> tm = (KafkaTransactionManager<?, ?>) container.getContainerProperties()
//                    .getTransactionManager();
//            tm.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
