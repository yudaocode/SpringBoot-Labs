package cn.iocoder.springboot.lab65.demo.config;

import cn.iocoder.springboot.lab65.demo.feign.UserServiceFeignClient;
import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import feign.soap.SOAPEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final JAXBContextFactory JAXB_FACTORY = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8")
            .build();

    @Bean
    public UserServiceFeignClient userServiceFeignClient() {
        return Feign.builder()
                .encoder(new SOAPEncoder(JAXB_FACTORY))
                .decoder(new SOAPDecoder(JAXB_FACTORY))
                .target(UserServiceFeignClient.class, "http://127.0.0.1:8080/ws"); // 目标地址
    }

}
