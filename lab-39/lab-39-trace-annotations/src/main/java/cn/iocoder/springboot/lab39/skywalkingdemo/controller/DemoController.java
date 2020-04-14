package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/trace_annotations")
    @Trace(operationName = "trace_annotations")
    public String echo() {
        // 自定义 SkyWalking Span
        ActiveSpan.tag("mp", "芋道源码");

        // 返回
        return "trace_annotations";
    }

}
