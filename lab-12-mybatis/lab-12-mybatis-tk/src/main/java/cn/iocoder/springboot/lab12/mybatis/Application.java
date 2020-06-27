package cn.iocoder.springboot.lab12.mybatis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab12.mybatis.mapper") // 注意，要换成 tk 提供的 @MapperScan 注解
public class Application {
}
