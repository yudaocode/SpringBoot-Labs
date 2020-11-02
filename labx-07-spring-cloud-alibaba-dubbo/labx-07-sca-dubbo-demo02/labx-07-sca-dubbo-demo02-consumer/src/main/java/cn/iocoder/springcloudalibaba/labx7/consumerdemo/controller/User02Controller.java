package cn.iocoder.springcloudalibaba.labx7.consumerdemo.controller;

import cn.iocoder.springcloudalibaba.labx7.consumerdemo.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.consumerdemo.dto.UserDTO;
import cn.iocoder.springcloudalibaba.labx7.consumerdemo.feign.UserFeignClient02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user02")
public class User02Controller {

    @Autowired
    private UserFeignClient02 userFeignClient;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userFeignClient.get(id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return userFeignClient.add(addDTO);
    }

}
