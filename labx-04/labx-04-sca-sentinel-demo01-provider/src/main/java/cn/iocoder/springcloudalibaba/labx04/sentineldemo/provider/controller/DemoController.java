package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // 测试【流量控制】
    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    // 测试【熔断降级】
    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
        return "sleep";
    }

    // 测试【热点参数限流】
    @GetMapping("/product_info")
    @SentinelResource("demo_product_info_hot")
    public String productInfo(Integer id) {
        return "商品编号：" + id;
    }

}
