package cn.iocoder.springboot.lab01.shirodemo.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        // 创建 SimpleAccountRealm 对象
        SimpleAccountRealm realm = new SimpleAccountRealm();
        // 添加两个用户。参数分别是 username、password、roles 。
        realm.addAccount("admin", "admin", "ADMIN");
        realm.addAccount("normal", "normal", "NORMAL");
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        // 创建 DefaultWebSecurityManager 对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置其使用的 Realm
        securityManager.setRealm(this.realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        // 创建 ShiroFilterFactoryBean 对象，用于创建 ShiroFilter 过滤器
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        // 设置 SecurityManager
        filterFactoryBean.setSecurityManager(this.securityManager());

        // 设置 URL 们
        filterFactoryBean.setLoginUrl("/login"); // 登陆 URL
        filterFactoryBean.setSuccessUrl("/login_success"); // 登陆成功 URL
        filterFactoryBean.setUnauthorizedUrl("/unauthorized"); // 无权限 URL

        // 设置 URL 的权限配置
        filterFactoryBean.setFilterChainDefinitionMap(this.filterChainDefinitionMap());

        return filterFactoryBean;
    }

    private Map<String, String> filterChainDefinitionMap() {
        Map<String, String> filterMap = new LinkedHashMap<>(); // 注意要使用有序的 LinkedHashMap ，顺序匹配
        filterMap.put("/test/echo", "anon"); // 允许匿名访问
        filterMap.put("/test/admin", "roles[ADMIN]"); // 需要 ADMIN 角色
        filterMap.put("/test/normal", "roles[NORMAL]"); // 需要 NORMAL 角色
        filterMap.put("/logout", "logout"); // 退出
        filterMap.put("/**", "authc"); // 默认剩余的 URL ，需要经过认证
        return filterMap;
    }

}
