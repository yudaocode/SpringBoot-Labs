package cn.iocoder.springboot.lab61.catdemo.config;

import com.dianping.cat.servlet.CatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatFilterConfigure {

    @Bean
    public FilterRegistrationBean<CatFilter> catFilter() {
        // 创建 CatFilter 对象
        CatFilter filter = new CatFilter();
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CatFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*"); // 匹配所有 URL
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }

}
