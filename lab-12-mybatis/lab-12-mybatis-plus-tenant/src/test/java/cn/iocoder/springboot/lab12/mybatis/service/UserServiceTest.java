package cn.iocoder.springboot.lab12.mybatis.service;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.context.TenantHolder;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserAsync() throws ExecutionException, InterruptedException {
        TenantHolder.setTenantId(10); // TODO 芋艿：写死
        Future<UserDO> future = userService.getUserAsync(9);
        future.get();
    }

}
