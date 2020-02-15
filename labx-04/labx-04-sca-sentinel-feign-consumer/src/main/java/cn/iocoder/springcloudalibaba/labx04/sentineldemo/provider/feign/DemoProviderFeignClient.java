package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.feign;

import cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.fallback.DemoProviderFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo-provider", url = "http://127.0.0.1:8080",
    fallbackFactory = DemoProviderFeignClientFallbackFactory.class)
public interface DemoProviderFeignClient {

    @GetMapping("/demo/echo")
    String echo();

}
