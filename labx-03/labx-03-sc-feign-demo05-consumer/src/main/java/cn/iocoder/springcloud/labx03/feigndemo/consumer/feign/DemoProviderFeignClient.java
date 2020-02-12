package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "demo-provider")
@FeignClient(name = "iocoder", url = "http://www.iocoder.cn") // 注意，保持 name 属性和 url 属性的 host 是一致的。
public interface DemoProviderFeignClient {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

    @GetMapping("/") // 请求首页
    String echo(@RequestParam("name") String name);

}
