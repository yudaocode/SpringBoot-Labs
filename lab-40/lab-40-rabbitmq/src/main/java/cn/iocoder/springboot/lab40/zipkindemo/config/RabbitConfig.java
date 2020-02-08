package cn.iocoder.springboot.lab40.zipkindemo.config;

import cn.iocoder.springboot.lab40.zipkindemo.message.DemoMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 创建 Queue
    @Bean
    public Queue demoQueue() {
        return new Queue(DemoMessage.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }

    // 创建 Direct Exchange
    @Bean
    public DirectExchange demoExchange() {
        return new DirectExchange(DemoMessage.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Binding
    // Exchange：DemoMessage.EXCHANGE
    // Routing key：DemoMessage.ROUTING_KEY
    // Queue：DemoMessage.QUEUE
    @Bean
    public Binding demoBinding() {
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(DemoMessage.ROUTING_KEY);
    }

}
