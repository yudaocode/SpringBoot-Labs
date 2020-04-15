package cn.iocoder.springcloud.labx13.springmvcdemo.controller;

import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Tracer tracer;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        // 创建一个 Span
        tracer.buildSpan("custom_operation").withTag("mp", "芋道源码").start().finish();

        return "user:" + id;
    }

}
