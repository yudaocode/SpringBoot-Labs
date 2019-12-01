package cn.iocoder.springboot.lab30.rpc;

import cn.iocoder.springboot.lab30.rpc.api.UserRpcService;
import cn.iocoder.springboot.lab30.rpc.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class ConsumerApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
    }

    @Component
    public class UserRpcServiceTest implements CommandLineRunner {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Resource
        private UserRpcService userRpcService;

        @Override
        public void run(String... args) throws Exception {
            UserDTO user = userRpcService.get(1);
            logger.info("[run][发起一次 Dubbo RPC 请求，获得用户为({})", user);
        }

    }

}
