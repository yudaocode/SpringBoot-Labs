package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import cn.iocoder.springcloud.labx03.feigndemo.provider.api.TestService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient extends TestService {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

}
