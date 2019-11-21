package cn.iocoder.springboot.lab25.springwebsocket.client.handler;

import cn.iocoder.springboot.lab25.springwebsocket.message.SendToUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class SendToUserRequestHandler extends StompSessionHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return SendToUserRequest.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        SendToUserRequest request = (SendToUserRequest) payload;
        logger.info("[handleFrame][接收到消息 headers({}) payload({})]", headers, request);
    }
}
