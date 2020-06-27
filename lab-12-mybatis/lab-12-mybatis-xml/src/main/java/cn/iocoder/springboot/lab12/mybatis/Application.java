package cn.iocoder.springboot.lab12.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab12.mybatis.mapper")
public class Application {
}
