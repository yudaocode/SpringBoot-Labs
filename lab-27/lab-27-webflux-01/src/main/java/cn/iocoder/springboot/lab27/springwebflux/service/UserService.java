package cn.iocoder.springboot.lab27.springwebflux.service;

import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }

}
