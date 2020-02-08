package cn.iocoder.springboot.lab44.nacosdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @NacosPropertySource(dataId = "example", type = ConfigType.YAML)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
