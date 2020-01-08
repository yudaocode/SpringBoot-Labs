package cn.iocoder.springboot.lab40.zipkindemo.message;

import java.io.Serializable;

public class DemoMessage implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_";

    /**
     * 编号
     */
    private Integer id;

    public DemoMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "id=" + id +
                '}';
    }

}
