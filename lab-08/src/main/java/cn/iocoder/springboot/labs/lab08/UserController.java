package cn.iocoder.springboot.labs.lab08;

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
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get_user_id")
    public String getUserId(@RequestParam("token") String token) {
        String userIdStr = stringRedisTemplate.opsForValue().get(token);
        if (userIdStr != null) {
//            return Integer.valueOf(userIdStr);
            return userIdStr;
        }
        return null;
    }

}
