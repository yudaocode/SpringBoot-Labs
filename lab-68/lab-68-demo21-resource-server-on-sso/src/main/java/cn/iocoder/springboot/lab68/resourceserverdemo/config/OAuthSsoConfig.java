package cn.iocoder.springboot.lab68.resourceserverdemo.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;

/**
 * SSO 配置
 *
 * 推荐看 SsoSecurityConfigurer 类
 */
@Configuration
@EnableOAuth2Sso // 开启 Sso 功能
public class OAuthSsoConfig {

}
