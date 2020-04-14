package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.controller;

import cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.feign.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/echo")
    public String echo() {
        return demoProviderFeignClient.echo();
    }

}
