package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;

public class Demo14Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_14";

    public static final String EXCHANGE = "EXCHANGE_DEMO_14";

    public static final String ROUTING_KEY = "ROUTING_KEY_14";

    /**
     * 编号
     */
    private Integer id;

    public Demo14Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo14Message{" +
                "id=" + id +
                '}';
    }

}
