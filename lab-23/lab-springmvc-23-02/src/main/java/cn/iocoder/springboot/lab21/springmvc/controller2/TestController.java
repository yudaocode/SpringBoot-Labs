package cn.iocoder.springboot.lab21.springmvc.controller2;

import cn.iocoder.springboot.lab21.springmvc.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 测试 Controller
 *
 * 这个类的目的，主要是为了测试 {@link cn.iocoder.springboot.lab21.springmvc.core.web.GlobalResponseBodyHandler} ，不拦截处理这个包
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 获得指定用户编号的用户
     *
     * @return 用户
     */
    @GetMapping("/get")
    public UserVO get() {
        return new UserVO().setId(1).setUsername(UUID.randomUUID().toString());
    }

}
