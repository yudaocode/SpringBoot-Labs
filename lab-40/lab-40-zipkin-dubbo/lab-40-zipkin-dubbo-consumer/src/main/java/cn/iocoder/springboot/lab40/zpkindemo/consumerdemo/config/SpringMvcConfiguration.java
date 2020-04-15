package cn.iocoder.springboot.lab40.zpkindemo.consumerdemo.config;

import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import(SpanCustomizingAsyncHandlerInterceptor.class)
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    public SpanCustomizingAsyncHandlerInterceptor webMvcTracingCustomizer;

    /**
     * Decorates server spans with application-defined web tags
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcTracingCustomizer);
    }

}
