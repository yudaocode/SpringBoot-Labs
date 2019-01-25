package cn.iocoder.springboot.labs.lab04;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {

    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    public static final Integer PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 创建交换器：direct、持久化、不自动删除
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        // 创建队列：持久化、非排他、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//        channel.queueDeclare(QUEUE_NAME + "_2", true, false, false, null);
        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
//        channel.queueBind(QUEUE_NAME + "_2", EXCHANGE_NAME, ROUTING_KEY);

        // 发送一条消息
        for (int i = 0; i < 1000; i++) {
            String message = "Hello World" + i;
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }

        // 关闭
        channel.close();
        connection.close();
    }

}
