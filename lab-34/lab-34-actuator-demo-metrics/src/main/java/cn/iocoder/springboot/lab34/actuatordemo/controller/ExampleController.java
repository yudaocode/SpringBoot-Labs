package cn.iocoder.springboot.lab34.actuatordemo.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 参考 https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-spring-mvc 文档
// 需要设置 management.metrics.web.server.request.autotime.enabled = false
@RestController
@RequestMapping("/example")
@Timed
public class ExampleController {

    @GetMapping("/visit")
//    @Counted(value = "example.visit.count", description = "example 访问次数"
    @Timed(value = "all.people", longTask = true)
    public String visit() {
        return "Example 示例";
    }

}
