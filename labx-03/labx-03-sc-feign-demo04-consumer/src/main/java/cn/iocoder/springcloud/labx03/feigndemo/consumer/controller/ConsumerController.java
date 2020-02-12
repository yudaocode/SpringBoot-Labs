package cn.iocoder.springcloud.labx03.feigndemo.consumer.controller;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.dto.DemoDTO;
import cn.iocoder.springcloud.labx03.feigndemo.consumer.feign.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/hello02")
    public String hello02(String name) {
        // 使用 Feign 调用接口
        String response = demoProviderFeignClient.echo(name);
        // 返回结果
        return "consumer:" + response;
    }

    @GetMapping("/test_get_demo")
    public DemoDTO testGetDemo(@RequestParam("type") int type, DemoDTO demoDTO) {
        // 方式一
        if (type == 1) {
            return demoProviderFeignClient.getDemo(demoDTO);
        } else if (type == 2) {
            return demoProviderFeignClient.getDemo(demoDTO.getUsername(), demoDTO.getPassword());
        } else {
            // 方式三
            Map<String, Object> params = new HashMap<>();
            params.put("username", demoDTO.getUsername());
            params.put("password", demoDTO.getPassword());
            return demoProviderFeignClient.getDemo(params);
        }
    }

    @GetMapping("/test_post_demo")
    public DemoDTO testPostDemo(DemoDTO demoDTO) {
       return demoProviderFeignClient.postDemo(demoDTO);
    }

}
