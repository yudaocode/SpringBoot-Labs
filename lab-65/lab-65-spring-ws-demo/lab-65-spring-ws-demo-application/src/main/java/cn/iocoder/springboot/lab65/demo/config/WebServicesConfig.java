package cn.iocoder.springboot.lab65.demo.config;

import cn.iocoder.springboot.lab65.demo.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebServicesConfig {

    // 创建 Jaxb2Marshaller Bean，实现 XML 与 Bean 的互相转换
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cn.iocoder.springboot.lab65.demo.wsdl"); // 用户服务的 WSDL 文件
        return marshaller;
    }

    // 创建 UserClient Bean
    @Bean
    public UserClient countryClient(Jaxb2Marshaller marshaller) {
        UserClient client = new UserClient();
        client.setDefaultUri("http://127.0.0.1:8080/ws"); // 用户服务的 Web Services 地址
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
