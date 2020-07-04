package cn.iocoder.springboot.labs.lab01.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

// 资源服务配置
@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 对 "/api/**" 开启认证
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/api/**");
    }

}

// 实际，OAuth2ResourceServer 不是和 OAuth2AuthorizationServer 一起。
// 主要考虑，简化 demo ，所以改成这样。