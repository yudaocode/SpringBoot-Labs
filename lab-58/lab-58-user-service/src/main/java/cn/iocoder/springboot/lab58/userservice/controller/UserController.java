package cn.iocoder.springboot.lab58.userservice.controller;

import cn.iocoder.springboot.lab58.userservice.request.UserAddRequest;
import cn.iocoder.springboot.lab58.userservice.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get") // 获得指定用户
    public UserResponse get(@RequestParam("id") Integer id) {
        return new UserResponse().setId(id)
                .setName("昵称：" + id).setGender(id % 2 == 0 ? 1 : 2);
    }

    @GetMapping("/list") // 获得匹配的用户列表
    public List<UserResponse> list(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "gender", required = false) Integer gender) {
        List<UserResponse> users = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            users.add(new UserResponse().setId(id)
                .setName(name + "_" + id).setGender(gender));
        }
        return users;
    }

    @PostMapping("/add") // 添加用户
    public Integer add(@RequestBody UserAddRequest request) {
        System.out.println("昵称：" + request.getName());
        System.out.println("性别：" + request.getGender());
        return 1;
    }

}
