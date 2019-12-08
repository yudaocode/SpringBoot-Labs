package cn.iocoder.springboot.lab04.rabbitmqdemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitMQConsumer {

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
        final Channel channel = connection.createChannel();
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息数量为 64 。
        // 创建交换器：direct、持久化、不自动删除
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("线程：" + Thread.currentThread() +  "：消息：" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignore) {
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        channel.basicConsume(QUEUE_NAME, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }

}
