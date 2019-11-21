package cn.iocoder.springboot.lab25.springwebsocket;

import cn.iocoder.springboot.lab25.springwebsocket.message.SendToAllRequest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
        WebSocketClient transport = new SockJsClient(transports); // TODO 芋艿，参考 https://codeday.me/bug/20190115/521240.html 文章
//        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(transport);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler() {

            @Override
            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
//                stompSession.subscribe("/topic/send_to_all", this);
                stompSession.send("/app/send_to_all", new SendToAllRequest().setMsgId(UUID.randomUUID().toString())
                    .setContent("测试消息"));
            }

            @Override
            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
                System.out.println();
            }

            @Override
            public void handleTransportError(StompSession stompSession, Throwable throwable) {
                System.out.println();
            }

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                System.out.println();
            }
        };

        stompClient.connect("ws://127.0.0.1:8080/", sessionHandler);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }

}
