package cn.iocoder.springboot.lab34.actuatordemo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * DEMO 访问次数 Metrics
     */
    private static final Counter METRICS_DEMO_COUNT = Counter
            .builder("demo.visit.count") // 指标的名字
            .description("demo 访问次数") // 指标的描述
            .baseUnit("次") // 指标的单位
            .tag("test", "nicai") // 自定义标签
            .register(Metrics.globalRegistry); // 注册到全局 MeterRegistry 指标注册表
//    private static final Counter METRICS_DEMO_COUNT = Metrics.counter("demo.visit.count");

    @GetMapping("/visit")
    public String visit() {
        // 增加次数
        METRICS_DEMO_COUNT.increment();
        return "Demo 示例";
    }

}
