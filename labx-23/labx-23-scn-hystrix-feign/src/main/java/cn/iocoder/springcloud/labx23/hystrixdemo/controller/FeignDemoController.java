package cn.iocoder.springcloud.labx23.hystrixdemo.controller;

import cn.iocoder.springcloud.labx23.hystrixdemo.feign.UserServiceFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-demo")
public class FeignDemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping("/get_user")
    public String getUser(@RequestParam("id") Integer id) {
        logger.info("[getUser][准备调用 user-service 获取用户({})详情]", id);
        return userServiceFeignClient.getUser(id);
    }

}
