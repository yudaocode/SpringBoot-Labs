package cn.iocoder.springcloud.labx21.zuuldemo;

import com.alibaba.cloud.sentinel.gateway.ConfigConstants;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 开启 Zuul 网关
public class ZuulApplication {

    public static void main(String[] args) {
        System.setProperty(SentinelConfig.APP_TYPE, ConfigConstants.APP_TYPE_ZUUL_GATEWAY); // 【重点】设置应用类型为 Zuul
        SpringApplication.run(ZuulApplication.class, args);
    }

}
