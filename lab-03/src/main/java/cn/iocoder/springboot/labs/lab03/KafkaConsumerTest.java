package cn.iocoder.springboot.labs.lab03;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

public class KafkaConsumerTest {

    public static void main(String[] args) {
        demo001();
    }

    public static void demo001() {
        // 创建消费者
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "yudaoyuanma"); // 消费分组
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", true);
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 消费消息
        consumer.subscribe(Collections.singletonList("test"), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

            }

            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
        while (true) {
            ConsumerRecords records = consumer.poll(10000);
            Iterator<ConsumerRecord> iterator = records.<ConsumerRecord>iterator();
            while (iterator.hasNext()) {
                ConsumerRecord record = iterator.next();
                System.out.println(record.key() + "\t" + record.value());
            }
//            System.out.println(records.count());
        }
    }

}
