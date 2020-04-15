package cn.iocoder.springcloud.labx15.adminserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity // 开启 Security 对 WebFlux 的安全功能
public class SecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        // 创建一个用户
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        // 如果胖友有更多用户的诉求，这里可以继续创建

        // 创建 MapReactiveUserDetailsService
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> // 设置权限配置
                exchanges
                        .pathMatchers("/assets/**").permitAll() // 静态资源，允许匿名访问
                        .pathMatchers("/login").permitAll() // 登陆接口，允许匿名访问
                        .anyExchange().authenticated() //
        )
        .formLogin().loginPage("/login") // 登陆页面
        .and().logout().logoutUrl("/logout") // 登出界面
        .and().httpBasic() // HTTP Basic 认证方式
        .and().csrf().disable(); // csrf 禁用
        return http.build();
    }

}
