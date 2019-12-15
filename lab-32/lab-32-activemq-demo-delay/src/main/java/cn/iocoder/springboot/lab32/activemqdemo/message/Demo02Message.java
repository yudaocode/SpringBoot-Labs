package cn.iocoder.springboot.lab32.activemqdemo.message;

import java.io.Serializable;

public class Demo02Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_02";

    /**
     * 编号
     */
    private Integer id;

    public Demo02Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo02Message{" +
                "id=" + id +
                '}';
    }

}
