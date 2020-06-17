package cn.iocoder.springboot.lab65.demo.config;

import https.github_com.yunaiv.springboot_labs.tree.master.lab_65.lab_65_cxf_ws_demo.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CXFConfig {

    @Bean
    public UserService userService() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置 UserService 接口
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        // 设置 Web Services 地址
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:9090/ws/user");
        // 创建
        return (UserService) jaxWsProxyFactoryBean.create();
    }

}
