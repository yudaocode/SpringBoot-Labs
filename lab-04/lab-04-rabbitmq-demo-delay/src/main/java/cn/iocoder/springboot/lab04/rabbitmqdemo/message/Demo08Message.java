package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;

public class Demo08Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_08"; // 正常队列
    public static final String DELAY_QUEUE = "DELAY_QUEUE_DEMO_08"; // 延迟队列（死信队列）

    public static final String EXCHANGE = "EXCHANGE_DEMO_08";

    public static final String ROUTING_KEY = "ROUTING_KEY_08"; // 正常路由键
    public static final String DELAY_ROUTING_KEY = "DELAY_ROUTING_KEY_08"; // 延迟路由键（死信路由键）

    /**
     * 编号
     */
    private Integer id;

    public Demo08Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo08Message{" +
                "id=" + id +
                '}';
    }

}
