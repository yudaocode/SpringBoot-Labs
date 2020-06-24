package cn.iocoder.springboot.lab68.resourceserverdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig {

}

// 实际，OAuth2ResourceServer 不是和 OAuth2AuthorizationServer 一起。
// 主要考虑，简化 demo ，所以改成这样。
