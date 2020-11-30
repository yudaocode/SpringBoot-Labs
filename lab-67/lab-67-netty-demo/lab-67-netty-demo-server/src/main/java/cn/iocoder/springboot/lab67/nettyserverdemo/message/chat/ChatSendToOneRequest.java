package cn.iocoder.springboot.lab67.nettyserverdemo.message.chat;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.Message;

/**
 * 发送给指定人的私聊消息 Request
 */
public class ChatSendToOneRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public String getToUser() {
        return toUser;
    }

    public ChatSendToOneRequest setToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public ChatSendToOneRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatSendToOneRequest setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "ChatSendToOneRequest{" +
                "toUser='" + toUser + '\'' +
                ", msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
