package cn.iocoder.springcloud.lab60.controller;

import cn.iocoder.springcloud.lab60.dto.UserCreateDTO;
import org.dromara.soul.client.common.annotation.SoulClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get")
    @SoulClient(path = "/user/get", desc = "获得用户详细")
    public String getUser(@RequestParam("id") Integer id) {
        return "DEMO:" + id;
    }

    @PostMapping("/create")
    @SoulClient(path = "/user/create", desc = "创建用户")
    public Integer createUser(@RequestBody UserCreateDTO createDTO) {
        logger.info("[createUser][username({}) password({})]", createDTO.getNickname(), createDTO.getGender());
        return 1;
    }

}
