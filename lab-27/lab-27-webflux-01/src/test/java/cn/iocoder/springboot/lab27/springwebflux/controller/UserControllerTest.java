package cn.iocoder.springboot.lab27.springwebflux.controller;

import cn.iocoder.springboot.lab27.springwebflux.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * UserController 集成测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureWebTestClient
public class UserControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testList() {
        webClient.get().uri("/users/list")
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"username\": \"yudaoyuanma\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"username\": \"woshiyutou\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"username\": \"chifanshuijiao\"\n" +
                "    }\n" +
                "]"); // 响应结果
    }

    @Test
    public void testGet() {
        // 获得指定用户编号的用户
        webClient.get().uri("/users/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("{\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"username:1\"\n" +
                "}"); // 响应结果
    }

    @Test
    public void testGet2() {
        // 获得指定用户编号的用户
        webClient.get().uri("/users/v2/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("{\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"test\"\n" +
                "}"); // 响应结果
    }

    @Test
    public void testAdd() {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "yudaoyuanma");
        params.put("password", "nicai");
        // 添加用户
        webClient.post().uri("/users/add")
                .bodyValue(params)
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("1"); // 响应结果。因为没有提供 content 的比较，所以只好使用 json 来比较。竟然能通过
    }

    @Test
    public void testAdd2() { // 发送文件的测试，可以参考 https://dev.to/shavz/sending-multipart-form-data-using-spring-webtestclient-2gb7 文章
        BodyInserters.FormInserter<String> formData = // Form Data 数据，需要这么拼凑
                BodyInserters.fromFormData("username", "yudaoyuanma")
                .with("password", "nicai");
        // 添加用户
        webClient.post().uri("/users/add2")
                .body(formData)
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody().json("1"); // 响应结果。因为没有提供 content 的比较，所以只好使用 json 来比较。竟然能通过
    }


    @Test
    public void testUpdate() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("username", "yudaoyuanma");
        // 修改用户
        webClient.post().uri("/users/update")
                .bodyValue(params)
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody(Boolean.class) // 期望返回值类型是 Boolean
                .consumeWith((Consumer<EntityExchangeResult<Boolean>>) result -> // 通过消费结果，判断符合是 true 。
                        Assert.assertTrue("返回结果需要为 true", result.getResponseBody()));
    }

    @Test
    public void testDelete() {
        // 删除用户
        webClient.post().uri("/users/delete?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 响应状态码 200
                .expectBody(Boolean.class) // 期望返回值类型是 Boolean
                .isEqualTo(true); // 这样更加简洁一些
//                .consumeWith((Consumer<EntityExchangeResult<Boolean>>) result -> // 通过消费结果，判断符合是 true 。
//                        Assert.assertTrue("返回结果需要为 true", result.getResponseBody()));
    }

}
