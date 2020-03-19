package cn.iocoder.springcloud.labx13.springmvcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        this.get("demo");
        return "success";
    }

    public void get(String key) {
        redisTemplate.opsForValue().get(key);
    }

}
