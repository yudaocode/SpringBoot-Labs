package cn.iocoder.springboot.lab67.nettycommondemo.codec;

public class Invocation {

    /**
     * 类型 - 心跳请求
     */
    public static final String TYPE_HEARTBEAT_REQUEST = "HEARTBEAT_REQUEST";
    /**
     * 类型 - 心跳响应
     */
    public static final String TYPE_HEARTBEAT_RESPONSE = "HEARTBEAT_RESPONSE";

    /**
     * 类型
     */
    private String type;
    /**
     * 消息
     */
    private String message;

    public String getType() {
        return type;
    }

    public Invocation setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Invocation setMessage(String message) {
        this.message = message;
        return this;
    }

}
