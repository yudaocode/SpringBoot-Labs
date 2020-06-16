package cn.iocoder.springboot.lab65.demo.config;

import https.github_com.yunaiv.springboot_labs.tree.master.lab_65.lab_65_spring_ws_demo.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServicesConfig {

    @Bean
    public UserService userService() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:8080/ws/user");
        return (UserService) jaxWsProxyFactoryBean.create();
    }

}
