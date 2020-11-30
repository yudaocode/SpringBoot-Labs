package cn.iocoder.springboot.lab03.kafkademo;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerMain {

    private static Consumer<String, String> createConsumer() {
        // 设置 Producer 的属性
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092"); // 设置 Broker 的地址
        properties.put("group.id", "demo-consumer-group"); // 消费者分组
        properties.put("auto.offset.reset", "earliest"); // 设置消费者分组最初的消费进度为 earliest 。可参考博客 https://blog.csdn.net/lishuangzhe7047/article/details/74530417 理解
        properties.put("enable.auto.commit", true); // 是否自动提交消费进度
        properties.put("auto.commit.interval.ms", "1000"); // 自动提交消费进度频率
        properties.put("key.deserializer", StringDeserializer.class.getName()); // 消息的 key 的反序列化方式
        properties.put("value.deserializer", StringDeserializer.class.getName()); // 消息的 value 的反序列化方式

        // 创建 KafkaProducer 对象
        // 因为我们消息的 key 和 value 都使用 String 类型，所以创建的 Producer 是 <String, String> 的泛型。
        return new KafkaConsumer<>(properties);
    }

    public static void main(String[] args) {
        // 创建 KafkaConsumer 对象
        Consumer<String, String> consumer = createConsumer();

        // 订阅消息
        consumer.subscribe(Collections.singleton("TestTopic"));

        // 拉取消息
        while (true) {
            // 拉取消息。如果拉取不到消息，阻塞等待最多 10 秒，或者等待拉取到消息。
            ConsumerRecords records = consumer.poll(Duration.ofSeconds(10));
            // 遍历处理消息
            records.forEach(new java.util.function.Consumer<ConsumerRecord>() {

                @Override
                public void accept(ConsumerRecord record) {
                    System.out.println(record.key() + "\t" + record.value());
                }

            });
        }
    }

}
