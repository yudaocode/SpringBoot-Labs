package cn.iocoder.springboot.lab27.springwebflux.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
@Order(1)
public class DemoWebFilter implements WebFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        // 继续执行请求
        return webFilterChain.filter(serverWebExchange)
                .doOnSuccess(new Consumer<Void>() { // 执行成功后回调

                    @Override
                    public void accept(Void aVoid) {
                        logger.info("[accept][执行成功]");
                    }

                });
    }

}
