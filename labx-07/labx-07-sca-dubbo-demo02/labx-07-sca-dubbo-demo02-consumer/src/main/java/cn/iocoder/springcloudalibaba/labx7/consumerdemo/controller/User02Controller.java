package cn.iocoder.springcloudalibaba.labx7.consumerdemo.controller;

import cn.iocoder.springcloudalibaba.labx7.consumerdemo.dto.UserDTO;
import cn.iocoder.springcloudalibaba.labx7.consumerdemo.feign.UserFeignClient02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user02")
public class User02Controller {

    @Autowired
    private UserFeignClient02 userFeignClient02;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userFeignClient02.get(id);
    }

}
