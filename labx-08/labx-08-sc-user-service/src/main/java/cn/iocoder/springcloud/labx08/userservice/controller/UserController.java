package cn.iocoder.springcloud.labx08.userservice.controller;

import cn.iocoder.springcloud.labx08.userservice.dto.UserAddDTO;
import cn.iocoder.springcloud.labx08.userservice.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
