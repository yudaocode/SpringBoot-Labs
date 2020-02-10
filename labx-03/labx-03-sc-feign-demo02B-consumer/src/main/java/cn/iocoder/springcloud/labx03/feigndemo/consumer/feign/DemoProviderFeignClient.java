package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.config.DemoProviderFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-provider", configuration = DemoProviderFeignClientConfiguration.class)
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}
