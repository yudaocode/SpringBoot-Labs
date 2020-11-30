package cn.iocoder.springboot.lab67.nettyserverdemo.message.chat;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.Message;

/**
 * 聊天发送消息结果的 Response
 */
public class ChatSendResponse implements Message {

    public static final String TYPE = "CHAT_SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

    public String getMsgId() {
        return msgId;
    }

    public ChatSendResponse setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ChatSendResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChatSendResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ChatSendResponse{" +
                "msgId='" + msgId + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
