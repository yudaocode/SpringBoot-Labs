package cn.iocoder.springboot.lab49.lombokdemo.dataobject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDOTest {

    @Test
    public void demo01() {
        UserDO user = new UserDO();
        user.setUsername("username:1");
        user.setPassword("password:1");
        System.out.println(user);
    }

}
