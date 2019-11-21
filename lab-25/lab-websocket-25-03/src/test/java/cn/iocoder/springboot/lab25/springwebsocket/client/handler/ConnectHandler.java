package cn.iocoder.springboot.lab25.springwebsocket.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class ConnectHandler extends StompSessionHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("[afterConnected][session({}) 连接成功 connectedHeaders({})]",
                session, connectedHeaders);

        session.subscribe("/topic/send_to_all", new SendToUserRequestHandler());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("[handleException][session({}) command({}) headers({}) payload({}) 发生异常]",
                session, command, headers, payload, exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("[handleTransportError][session({}) 发生传输错误]", session, exception);
    }

}
