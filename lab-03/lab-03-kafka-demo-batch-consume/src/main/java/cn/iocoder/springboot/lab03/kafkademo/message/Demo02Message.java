package cn.iocoder.springboot.lab03.kafkademo.message;

/**
 * 示例 01 的 Message 消息
 */
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

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
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }

}
