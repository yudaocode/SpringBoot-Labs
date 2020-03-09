package cn.iocoder.springcloud.labx09.apollodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class);

        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }

}
