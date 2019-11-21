package cn.iocoder.springboot.lab25.springwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 开启 Spring WebSocket 对 Stomp 的支持
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/send_to_all")
//                .setAllowedOrigins("*")
//                .withSockJS();
        registry.addEndpoint("/")
                .setAllowedOrigins("*")
                .withSockJS();

//        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
//        registry.addEndpoint("/")
//                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
//                .setAllowedOrigins("*");
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(this.webSocketHandler(), "/") // 配置处理器
//                .addInterceptors(new DemoWebSocketShakeInterceptor()) // 配置拦截器
//                .setAllowedOrigins("*"); // 解决跨域问题
//    }

//    @Bean
//    public DemoWebSocketHandler webSocketHandler() {
//        return new DemoWebSocketHandler();
//    }

//    @Bean
//    public DemoWebSocketShakeInterceptor webSocketShakeInterceptor() {
//        return new DemoWebSocketShakeInterceptor();
//    }

}
