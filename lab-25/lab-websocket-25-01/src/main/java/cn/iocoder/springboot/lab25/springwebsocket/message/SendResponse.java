package cn.iocoder.springboot.lab25.springwebsocket.message;

/**
 * 发送给单个人消息的成功结果的 Message
 */
public class SendResponse implements Message {

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;

    public String getMsgId() {
        return msgId;
    }

    public SendResponse setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public SendResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

}
