package cn.iocoder.springboot.lab68.resourceserverdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // 设置 /login 无需权限访问
            .antMatchers("/login").permitAll()
            // 设置 /client-login 无需权限访问
            .antMatchers("/client-login").permitAll()
            /// 设置 /callback 无需权限访问
            .antMatchers("/callback").permitAll()
            // 设置 /callback02 无需权限访问
            .antMatchers("/callback02").permitAll()
            // 设置其它请求，需要认证后访问
            .anyRequest().authenticated()
            ;
    }

}
