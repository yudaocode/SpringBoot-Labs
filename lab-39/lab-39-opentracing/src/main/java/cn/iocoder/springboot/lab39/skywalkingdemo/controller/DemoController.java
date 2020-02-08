package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/opentracing")
    public String echo() {
        // 创建一个 Span
        Tracer tracer = new SkywalkingTracer();
        tracer.buildSpan("custom_operation").withTag("mp", "芋道源码").startManual().finish();

        // 返回
        return "opentracing";
    }

}
