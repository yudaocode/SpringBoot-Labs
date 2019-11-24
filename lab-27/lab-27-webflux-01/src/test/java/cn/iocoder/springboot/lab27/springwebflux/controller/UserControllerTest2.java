package cn.iocoder.springboot.lab27.springwebflux.controller;

import cn.iocoder.springboot.lab27.springwebflux.service.UserService;
import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * UserController 单元测试
 *
 * 参考 https://howtodoinjava.com/spring-webflux/webfluxtest-with-webtestclient/ 文章
 */
@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
public class UserControllerTest2 {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserService userService;

    @Test
    public void testGet2() throws Exception {
        // Mock UserService 的 get 方法
        System.out.println("before mock:" + userService.get(1));
        Mockito.when(userService.get(1)).thenReturn(
                new UserVO().setId(1).setUsername("username:1"));
        System.out.println("after mock:" + userService.get(1));

        // 查询用户列表
        webClient.get().uri("/users/v2/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("{\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"username:1\"\n" +
                "}"); // 响应结果
    }

}
