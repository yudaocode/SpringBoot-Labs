package cn.iocoder.springboot.lab27.springwebflux.config;

import cn.iocoder.springboot.lab27.springwebflux.core.web.GlobalResponseBodyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Collections;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

    @Bean
    public GlobalResponseBodyHandler responseWrapper(ServerCodecConfigurer serverCodecConfigurer,
                                                     RequestedContentTypeResolver requestedContentTypeResolver) {
        return new GlobalResponseBodyHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 添加全局的 CORS 配置
//        registry.addMapping("/**") // 匹配所有 URL ，相当于全局配置
//                .allowedOrigins("*") // 允许所有请求来源
//                .allowCredentials(true) // 允许发送 Cookie
//                .allowedMethods("*") // 允许所有请求 Method
//                .allowedHeaders("*") // 允许所有请求 Header
////                .exposedHeaders("*") // 允许所有响应 Header
//                .maxAge(1800L); // 有效期 1800 秒，2 小时
//    }

    @Bean
    @Order(0) // 设置 order 排序。这个顺序很重要哦，为避免麻烦请设置在最前
    public CorsWebFilter corsFilter() {
        // 创建 UrlBasedCorsConfigurationSource 配置源，类似 CorsRegistry 注册表
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建 CorsConfiguration 配置，相当于 CorsRegistration 注册信息
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*")); // 允许所有请求来源
        config.setAllowCredentials(true); // 允许发送 Cookie
        config.addAllowedMethod("*"); // 允许所有请求 Method
        config.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求 Header
        // config.setExposedHeaders(Collections.singletonList("*")); // 允许所有响应 Header
        config.setMaxAge(1800L); // 有效期 1800 秒，2 小时
        source.registerCorsConfiguration("/**", config);
        // 创建 CorsWebFilter 对象
        return new CorsWebFilter(source); // 创建 CorsFilter 过滤器
    }


//    @Override
//    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        configurer.registerDefaults(false);
//        configurer.customCodecs().decoder(new Jaxb2XmlDecoder());   // <- here
//        configurer.customCodecs().encoder(new Jaxb2XmlEncoder());   // <- here
//    }

}
