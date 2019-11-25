package cn.iocoder.springboot.lab27.springwebflux.controller2;

import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 测试 Controller
 */
@RestController
@RequestMapping("/test")
//@CrossOrigin(origins = "*", allowCredentials = "true") // 允许所有来源，允许发送 Cookie
public class TestController {

    /**
     * 获得指定用户编号的用户
     *
     * @return 用户
     */
    @GetMapping("/get")
//    @CrossOrigin(allowCredentials = "false") // 允许所有来源，不允许发送 Cookie
    public Mono<UserVO> get() {
        // 查询用户
        UserVO user =  new UserVO().setId(1).setUsername(UUID.randomUUID().toString());
        // 返回
        return Mono.just(user);
    }

}
