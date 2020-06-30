package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis;

import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.cacheobject.UserCacheObject;
import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.service.UserService02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserService02Test {

    @Autowired
    private UserService02 userService;

    @Test
    public void testSet() {
        UserCacheObject object = new UserCacheObject()
                .setId(1)
                .setName("芋道源码")
                .setGender(1); // 男
        userService.set(object.getId(), object);
    }

}
