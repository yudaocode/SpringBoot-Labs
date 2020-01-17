package cn.iocoder.springboot.lab23.testdemo.controller;

import cn.iocoder.springboot.lab23.testdemo.dataobject.UserDO;
import cn.iocoder.springboot.lab23.testdemo.service.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * UserController 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGet() throws Exception {
        // Mock UserService 的 get 方法
        Mockito.when(userService.get(1)).thenReturn(
                new UserDO().setId(1).setUsername("username:1").setPassword("password:1"));

        // 查询用户
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/user/get?id=1"));

        // 校验响应状态码
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // 响应状态码 200

        // 校验响应内容方式一：直接全部匹配
        resultActions.andExpect(MockMvcResultMatchers.content().json("{\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"username:1\",\n" +
                "    \"password\": \"password:1\"\n" +
                "}", true)); // 响应结果

        // 校验响应内容方式二：逐个字段匹配
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("id", IsEqual.equalTo(1)));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("username", IsEqual.equalTo("username:1")));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("password", IsEqual.equalTo("password:1")));
    }

}
