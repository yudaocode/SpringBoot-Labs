package cn.iocoder.springboot.lab25.springwebsocket.controller;

import cn.iocoder.springboot.lab25.springwebsocket.message.SendToAllRequest;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SendController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @MessageMapping("/send_to_all")
    @SendTo(value = "/topic/send_to_all") // TODO https://blog.csdn.net/fly_leopard/article/details/78664409 暂时先跳过
    public SendToUserRequest sendToAll(SendToAllRequest message) {
        logger.info("[sendToAll][SendToAllRequest({})]", message);
//        // 创建转发的消息
//        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
//                .setContent(message.getContent());
//        // 广播发送
//        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToUserRequest);

        // 这里，假装直接成功
//        return new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        return new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
    }

//    @SubscribeMapping("/topic/send_to_all")
//    public void subSendToAll(SendToUserRequest message) {
//        logger.info("[subSendToAll][SendToUserRequest({})]", message);
//    }

}
