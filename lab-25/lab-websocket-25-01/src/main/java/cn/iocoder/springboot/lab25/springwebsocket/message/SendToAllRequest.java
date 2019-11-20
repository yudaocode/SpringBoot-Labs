package cn.iocoder.springboot.lab25.springwebsocket.message;

/**
 * 发送给所有人的群聊消息的 Message
 */
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public SendToAllRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public SendToAllRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

}
