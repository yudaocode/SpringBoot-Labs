package cn.iocoder.springboot.lab34.actuatordemo.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO 暂时没生效 https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-spring-mvc
@RestController
@RequestMapping("/example")
@Timed
public class ExampleController {

    @GetMapping("/visit")
    @Counted(value = "example.visit.count", description = "example 访问次数")
    public String visit() {
        return "Example 示例";
    }

}
