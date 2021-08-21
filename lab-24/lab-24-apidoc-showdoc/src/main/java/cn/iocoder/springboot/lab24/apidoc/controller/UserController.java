package cn.iocoder.springboot.lab24.apidoc.controller;

import cn.iocoder.springboot.lab24.apidoc.controller.vo.UserLoginReqVO;
import cn.iocoder.springboot.lab24.apidoc.controller.vo.UserLoginRespVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/login")
    public UserLoginRespVO login(@RequestBody UserLoginReqVO reqVO) {
        UserLoginRespVO respVO = new UserLoginRespVO();
        respVO.setUserId(1024);
        respVO.setUsername(reqVO.getUsername());
        respVO.setName("芋道源码");
        return respVO;
    }

}
