package cn.iocoder.springboot.lab23.testdemo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest2 {

    @SpyBean
    private UserService userService;

    @Test
    public void testAddSuccess() {
        System.out.println("testAddSuccess");
        // Mock UserService 的 exists 方法
        Mockito.when(userService.exists("username")).thenReturn(
                false);

        Assert.assertNotNull("注册返回为 null，注册失败",
                userService.add("username", "password"));
    }

    @Test
    public void testAddFailure() {
        System.out.println("testAddFailure");

        Assert.assertNull("注册返回为 null，注册失败",
                userService.add("username", "password"));
    }

}
