package cn.iocoder.springboot.lab27.springwebflux.controller;

import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 用户 Router
 */
@Configuration
public class UserRouter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public RouterFunction<ServerResponse> userListRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"),
                new HandlerFunction<ServerResponse>() {

                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        // 查询列表
                        List<UserVO> result = new ArrayList<>();
                        result.add(new UserVO().setId(1).setUsername("yudaoyuanma"));
                        result.add(new UserVO().setId(2).setUsername("woshiyutou"));
                        result.add(new UserVO().setId(3).setUsername("chifanshuijiao"));
                        // 返回列表
                        return ServerResponse.ok().bodyValue(result);
                    }

                });
    }

    @Bean
    public RouterFunction<ServerResponse> userGetRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/get"),
                new HandlerFunction<ServerResponse>() {

                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        // 获得编号
                        Integer id = request.queryParam("id")
                                .map(s -> StringUtils.isEmpty(s) ? null : Integer.valueOf(s)).get();
                        // 查询用户
                        UserVO user = new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
                        // 返回列表
                        return ServerResponse.ok().bodyValue(user);
                    }

                });
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction() {
        return route(GET("/users2/demo"), request -> ok().bodyValue("demo"));
    }

    @Bean
    public RouterFunction<ServerResponse> demo2RouterFunction() {
        return route(GET("/users2/demo2"), request -> ok().bodyValue("demo"))
                .filter(new HandlerFilterFunction<ServerResponse, ServerResponse>() {

                    @Override
                    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
                        return next.handle(request).doOnSuccess(new Consumer<ServerResponse>() { // 执行成功后回调

                            @Override
                            public void accept(ServerResponse serverResponse) {
                                logger.info("[accept][执行成功]");
                            }

                        });
                    }

                });
    }

}
