package cn.iocoder.springboot.lab27.springwebflux;

import cn.iocoder.springboot.lab27.springwebflux.core.web.GlobalResponseBodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

@SpringBootApplication
public class Application {

    @Autowired
    ServerCodecConfigurer serverCodecConfigurer;
    @Autowired
    RequestedContentTypeResolver requestedContentTypeResolver;

    @Bean
    public GlobalResponseBodyHandler responseWrapper() {
        return new GlobalResponseBodyHandler(serverCodecConfigurer
                .getWriters(), requestedContentTypeResolver);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
