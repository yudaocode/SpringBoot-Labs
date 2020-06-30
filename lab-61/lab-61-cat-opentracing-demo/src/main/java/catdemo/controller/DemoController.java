package catdemo.controller;

import com.dianping.cat.Cat;
import io.opentracing.Span;
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

    /**
     * 监控模型 Transaction 的示例
     */
    @GetMapping("/test")
    public String test() {
        // 创建一个 Span
        Span parentSpan = tracer.buildSpan("parent").start();

        Span childSpan = tracer.buildSpan("child").start();
        childSpan.finish();

        Cat.logEvent("测试事件", "123");

        // 结束一个 Span
        parentSpan.finish();

        // 返回
        return "opentracing";
    }

}
