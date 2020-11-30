package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

    @GetMapping("/get_demo") // GET 方式一，最推荐
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    @GetMapping("/get_demo") // GET 方式二，相对推荐
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/get_demo") // GET 方式三，不推荐
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    @PostMapping("/post_demo") // POST 方式
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);

}
