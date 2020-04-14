package cn.iocoder.springboot.lab39.skywalkingdemo.rocketmqdemo.message;

/**
 * 示例的 Message 消息
 */
public class DemoMessage {

    public static final String TOPIC = "DEMO";

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
