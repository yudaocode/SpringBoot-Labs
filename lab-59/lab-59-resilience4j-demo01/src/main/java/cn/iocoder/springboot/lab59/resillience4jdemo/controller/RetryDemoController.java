package cn.iocoder.springboot.lab59.resillience4jdemo.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/retry-demo")
public class RetryDemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get_user")
    @Retry(name = "backendE", fallbackMethod = "getUserFallback")
    public String getUser(@RequestParam("id") Integer id) {
        logger.info("[getUser][准备调用 user-service 获取用户({})详情]", id);
        return restTemplate.getForEntity("http://127.0.0.1:18080/user/get?id=" + id, String.class).getBody();
    }

    public String getUserFallback(Integer id, Throwable throwable) {
        logger.info("[getUserFallback][id({}) exception({})]", id, throwable.getClass().getSimpleName());
        return "mock:User:" + id;
    }

}
