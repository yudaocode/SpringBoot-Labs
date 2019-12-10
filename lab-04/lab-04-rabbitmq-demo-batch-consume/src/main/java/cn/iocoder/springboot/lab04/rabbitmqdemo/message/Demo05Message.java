package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;

public class Demo05Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_05";

    public static final String EXCHANGE = "EXCHANGE_DEMO_05";

    public static final String ROUTING_KEY = "ROUTING_KEY_05";

    /**
     * 编号
     */
    private Integer id;

    public Demo05Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo05Message{" +
                "id=" + id +
                '}';
    }

}
