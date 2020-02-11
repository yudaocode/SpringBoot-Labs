package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import cn.iocoder.springcloud.labx03.feigndemo.provider.api.ProviderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient extends ProviderService {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

}
