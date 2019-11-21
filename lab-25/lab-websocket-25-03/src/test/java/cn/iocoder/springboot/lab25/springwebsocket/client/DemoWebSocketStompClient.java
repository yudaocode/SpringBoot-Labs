package cn.iocoder.springboot.lab25.springwebsocket.client;

import cn.iocoder.springboot.lab25.springwebsocket.client.handler.ConnectHandler;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToAllRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class DemoWebSocketStompClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoWebSocketStompClient.class);

    private WebSocketStompClient stompClient;

    public DemoWebSocketStompClient() {
        // 创建 SockJsClient 对象，内嵌 StandardWebSocketClient 对象
        List<Transport> transports = Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
        WebSocketClient transport = new SockJsClient(transports);
        // 创建 WebSocketStompClient 对象，内嵌 SockJsClient 对象
        this.stompClient = new WebSocketStompClient(transport);
        // 设置消息转换器
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    public ListenableFuture<StompSession> connect(String url) {
        return this.stompClient.connect(url, new ConnectHandler());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建 DemoWebSocketStompClient 客户端
        DemoWebSocketStompClient demoWebSocketStompClient = new DemoWebSocketStompClient();
        // 发起连接
        ListenableFuture<StompSession> future = demoWebSocketStompClient.connect("ws://127.0.0.1:8080/");
        StompSession session = future.get();
        // 发起消息
        session.send("/app/send_to_all", new SendToAllRequest().setMsgId(UUID.randomUUID().toString())
                .setContent("测试消息"));

        // 阻塞等待
        new Scanner(System.in).nextLine(); // Don't close immediately.
    }

}
