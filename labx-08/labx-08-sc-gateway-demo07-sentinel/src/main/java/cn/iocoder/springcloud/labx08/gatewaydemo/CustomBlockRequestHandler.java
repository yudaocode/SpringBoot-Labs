package cn.iocoder.springcloud.labx08.gatewaydemo;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomBlockRequestHandler implements BlockRequestHandler {

    private static final String DEFAULT_BLOCK_MSG_PREFIX = "HAHAHA ~ Blocked by Sentinel: ";

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
    }

}
