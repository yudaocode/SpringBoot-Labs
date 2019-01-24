package cn.iocoder.springboot.labs.lab03;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            logger.info("[test{}]----------------- record =" + record, Thread.currentThread().getId());
            logger.info("[test{}]------------------ message =" + message, Thread.currentThread().getId());
        }

    }

    @KafkaListener(topics = {"yunai"})
    public void listen02(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            logger.info("[yunai{}]----------------- record =" + record, Thread.currentThread().getId());
            logger.info("[yunai{}]------------------ message =" + message, Thread.currentThread().getId());
        }

    }

    @KafkaListener(topics = {"afei"})
    public void listen03(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            logger.info("[afei{}]----------------- record =" + record, Thread.currentThread().getId());
            logger.info("[afei{}]------------------ message =" + message, Thread.currentThread().getId());
        }

    }

}
