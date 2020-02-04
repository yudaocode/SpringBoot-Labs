package cn.iocoder.springboot.lab49.lombokdemo.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService01 {

    public void test(@NonNull String username, String password) {

    }

}
