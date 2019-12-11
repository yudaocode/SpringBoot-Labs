package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;

public class Demo09Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_09";

    public static final String EXCHANGE = "EXCHANGE_DEMO_09";

    public static final String ROUTING_KEY = "ROUTING_KEY_09";

    /**
     * 编号
     */
    private Integer id;

    public Demo09Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo09Message{" +
                "id=" + id +
                '}';
    }

}
