package cn.iocoder.springboot.lab32.activemqdemo.message;

import java.io.Serializable;

/**
 * 广播消费的消息示例
 */
public class BroadcastMessage implements Serializable {

    public static final String TOPIC = "TOPIC_BROADCASTING";

    /**
     * 编号
     */
    private Integer id;

    public BroadcastMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BroadcastMessage{" +
                "id=" + id +
                '}';
    }

}
