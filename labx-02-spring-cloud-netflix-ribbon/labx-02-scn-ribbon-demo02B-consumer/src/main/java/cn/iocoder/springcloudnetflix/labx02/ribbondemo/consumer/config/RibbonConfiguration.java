package cn.iocoder.springcloudnetflix.labx02.ribbondemo.consumer.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbon.DefaultRibbonClientConfiguration;
import ribbon.UserProviderRibbonClientConfiguration;

@Configuration
@RibbonClients(
        value = {
                @RibbonClient(name = "demo-provider", configuration = UserProviderRibbonClientConfiguration.class) // 客户端级别的配置
        },
        defaultConfiguration = DefaultRibbonClientConfiguration.class // 全局配置
)
public class RibbonConfiguration {
}
