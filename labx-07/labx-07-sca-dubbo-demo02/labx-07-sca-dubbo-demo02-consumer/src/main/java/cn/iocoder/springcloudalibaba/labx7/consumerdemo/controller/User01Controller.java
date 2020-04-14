package cn.iocoder.springcloudalibaba.labx7.consumerdemo.controller;

import cn.iocoder.springcloudalibaba.labx7.consumerdemo.feign.UserFeignClient;
import cn.iocoder.springcloudalibaba.labx7.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user01")
public class User01Controller {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userFeignClient.get(id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return userFeignClient.add(addDTO);
    }

}
