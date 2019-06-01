package cn.iocoder.springboot.labs.lab07.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/hello.txt")
                    .uri("http://localhost:8000/hello.txt"))
                .route(r -> r.path("/**")
                        .uri("http://localhost:8080"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

}
