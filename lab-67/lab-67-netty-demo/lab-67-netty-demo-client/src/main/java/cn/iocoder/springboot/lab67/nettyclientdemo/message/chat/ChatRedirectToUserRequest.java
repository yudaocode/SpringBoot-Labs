package cn.iocoder.springboot.lab67.nettyclientdemo.message.chat;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.Message;

/**
 * 转发消息给一个用户的 Message
 */
public class ChatRedirectToUserRequest implements Message {

    public static final String TYPE = "CHAT_REDIRECT_TO_USER_REQUEST";

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

    public ChatRedirectToUserRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatRedirectToUserRequest setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "ChatRedirectToUserRequest{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
