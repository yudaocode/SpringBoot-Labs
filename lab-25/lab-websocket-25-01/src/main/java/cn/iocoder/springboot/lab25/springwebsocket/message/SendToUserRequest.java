package cn.iocoder.springboot.lab25.springwebsocket.message;

/**
 * 发送消息给一个用户的 Message
 */
public class SendToUserRequest implements Message {

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public SendToUserRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SendToUserRequest setContent(String content) {
        this.content = content;
        return this;
    }

}
