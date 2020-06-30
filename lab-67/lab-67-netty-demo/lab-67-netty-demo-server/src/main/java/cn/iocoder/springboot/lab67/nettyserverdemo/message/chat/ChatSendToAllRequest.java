package cn.iocoder.springboot.lab67.nettyserverdemo.message.chat;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.Message;

/**
 * 发送给所有人的群聊消息的 Message
 */
public class ChatSendToAllRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

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

    public ChatSendToAllRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public ChatSendToAllRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    @Override
    public String toString() {
        return "ChatSendToAllRequest{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
