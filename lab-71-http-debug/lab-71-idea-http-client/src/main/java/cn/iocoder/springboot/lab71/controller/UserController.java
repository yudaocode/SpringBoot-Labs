package cn.iocoder.springboot.lab71.controller;

import cn.iocoder.springboot.lab71.vo.UserUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户 Controller
 *
 * 示例代码，纯粹为了演示。
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        if ("yudaoyuanma".equals(username) && "123456".equals(password)) {
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("userId", 1);
            tokenMap.put("token", "token001");
            return tokenMap;
        }
        throw new RuntimeException("小朋友，你的账号密码不正确哟！");
    }

    @GetMapping("/user/get-current")
    public Map<String, Object> getCurrentUser(@RequestHeader("Authorization") String authorization,
                                              @RequestParam("full") boolean full) {
        if ("token001".equals(authorization)) {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", 1);
            // full 为 true 时，获得完整信息
            if (full) {
                userInfo.put("nickname", "芋道源码");
                userInfo.put("gender", 1);
            }
            return userInfo;
        }
        throw new RuntimeException("小朋友，你没有登录哟！");
    }

    @PostMapping("/user/update")
    public Boolean update(@RequestBody UserUpdateVO updateVO) {
        logger.info("[update][收到更新请求：{}]", updateVO.toString());
        return true;
    }

}
