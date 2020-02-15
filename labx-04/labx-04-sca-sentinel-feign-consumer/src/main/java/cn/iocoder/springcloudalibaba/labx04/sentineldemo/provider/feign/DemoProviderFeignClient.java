package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo-provider", url = "http://127.0.0.1:8080")
public interface DemoProviderFeignClient {

    @GetMapping("/demo/echo")
    String echo();

}
