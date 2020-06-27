package cn.iocoder.springboot.lab04.rabbitmqdemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitMQConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = RabbitMQProducer.getConnection();

        // 创建信道
        final Channel channel = connection.createChannel();
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息数量为 64 。

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 打印日志
                System.out.println(String.format("[线程：%s][路由键：%s][消息内容：%s]",
                        Thread.currentThread(), envelope.getRoutingKey(), new String(body)));
                // ack 消息已经消费
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        // 订阅消费 QUEUE_NAME 队列
        channel.basicConsume(RabbitMQProducer.QUEUE_NAME, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }

}
