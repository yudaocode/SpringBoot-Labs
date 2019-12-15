package cn.iocoder.springboot.lab32.activemqdemo.message;

import java.io.Serializable;

/**
 * 广播消费的消息示例
 */
public class ClusteringMessage implements Serializable {

    public static final String QUEUE = "QUEUE_CLUSTERING";

    /**
     * 编号
     */
    private Integer id;

    public ClusteringMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClusteringtMessage{" +
                "id=" + id +
                '}';
    }

}
