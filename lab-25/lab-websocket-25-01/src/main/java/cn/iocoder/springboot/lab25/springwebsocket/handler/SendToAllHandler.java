package cn.iocoder.springboot.lab25.springwebsocket.handler;

import cn.iocoder.springboot.lab25.springwebsocket.message.SendResponse;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToAllRequest;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToUserRequest;
import cn.iocoder.springboot.lab25.springwebsocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {

    @Override
    public void execute(Session session, SendToAllRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }

}
