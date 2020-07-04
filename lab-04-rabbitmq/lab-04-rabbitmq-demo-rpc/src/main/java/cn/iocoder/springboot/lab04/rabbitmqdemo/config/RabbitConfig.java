package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo14Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo01Queue() {
            return new Queue(Demo14Message.QUEUE, // Queue 名字
                    false, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo01Exchange() {
            return new DirectExchange(Demo14Message.EXCHANGE,
                    false,  // durable: 是否持久化
                    false);  // autoDelete: 是否自动删除
        }

        // 创建 Binding
        // Exchange：Demo01Message.EXCHANGE
        // Routing key：Demo01Message.ROUTING_KEY
        // Queue：Demo01Message.QUEUE
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo14Message.ROUTING_KEY);
        }

    }

}
