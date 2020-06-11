package cn.iocoder.springboot.lab63.rpc;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:motan.xml")
public class ProviderApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(ProviderApplication.class, args);
        // 设置 Motan 开启对外服务
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
    }

}
