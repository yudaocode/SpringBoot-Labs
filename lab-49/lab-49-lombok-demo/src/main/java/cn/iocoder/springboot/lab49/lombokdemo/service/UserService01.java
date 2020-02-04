package cn.iocoder.springboot.lab49.lombokdemo.service;

import cn.iocoder.springboot.lab49.lombokdemo.dataobject.UserDO;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService01 {

    @NonNull
    public void test01(UserDO userDO) {
        System.out.println(userDO.getUsername());
        System.out.println(userDO.getPassword());
    }

    public void test02(@NotNull String username, String password) {

    }

    public void test03() {
        @NonNull String username = "";
        String password = "";
    }


}
