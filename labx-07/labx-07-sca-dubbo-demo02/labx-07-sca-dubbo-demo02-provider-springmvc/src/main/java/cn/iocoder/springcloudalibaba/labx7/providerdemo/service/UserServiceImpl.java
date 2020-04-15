package cn.iocoder.springcloudalibaba.labx7.providerdemo.service;

import cn.iocoder.springcloudalibaba.labx7.api.UserService;
import cn.iocoder.springcloudalibaba.labx7.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0", protocol = {"dubbo"})
@RestController
@RequestMapping("/user")
public class UserServiceImpl implements UserService {

    @Override
    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    @PostMapping("/add")
    public Integer add(@RequestBody UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
