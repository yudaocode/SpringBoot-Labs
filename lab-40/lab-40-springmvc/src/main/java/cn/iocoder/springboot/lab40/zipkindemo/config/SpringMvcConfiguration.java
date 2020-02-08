package cn.iocoder.springboot.lab40.zipkindemo.config;

import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import(SpanCustomizingAsyncHandlerInterceptor.class) // 创建拦截器 SpanCustomizingAsyncHandlerInterceptor Bean
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    public SpanCustomizingAsyncHandlerInterceptor webMvcTracingCustomizer;

    /**
     * Decorates server spans with application-defined web tags
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 记录 SpringMVC 相关信息到 Span 中
        registry.addInterceptor(webMvcTracingCustomizer);
    }

}
