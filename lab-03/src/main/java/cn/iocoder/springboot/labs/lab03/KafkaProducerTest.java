package cn.iocoder.springboot.labs.lab03;

import cn.iocoder.springboot.labs.lab03.pojo.User;
import cn.iocoder.springboot.labs.lab03.serialize.JSONSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        demo001();
        demo002();
    }

    /**
     * 同步发送
     */
    public static void demo001() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
//        properties.put("acks", "all");
//        properties.put("retries", 0);
//        properties.put("batch.size", 16384);
//        properties.put("linger.ms", 1);
//        properties.put("client.id", "DemoProducer");
//        properties.put("buffer.memory", 33554432);

        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 发送消息
        Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("test", "key", "value"));
        System.out.println(future.get().topic());
    }

    public static void demo002() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");

        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", JSONSerializer.class.getName());
        Producer<String, Object> producer = new KafkaProducer<String, Object>(properties);

        // 发送消息
        User user = new User();
        user.setUsername("厮大牛逼");
        user.setPassword("狼哥很骚");
        Future<RecordMetadata> future = producer.send(new ProducerRecord<String, Object>("test", "key", user));
        System.out.println(future.get().offset());
    }

}
