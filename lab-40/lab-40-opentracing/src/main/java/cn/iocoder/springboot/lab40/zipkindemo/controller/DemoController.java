package cn.iocoder.springboot.lab40.zipkindemo.controller;

import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Tracer tracer;

    @GetMapping("/opentracing")
    public String echo() {
        // 创建一个 Span
        tracer.buildSpan("custom_operation").withTag("mp", "芋道源码").start().finish();

        // 返回
        return "opentracing";
    }

}
