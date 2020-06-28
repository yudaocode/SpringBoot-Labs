package cn.iocoder.springcloudalibaba.labx7.consumerdemo.controller;

import cn.iocoder.springcloudalibaba.labx7.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.dto.UserDTO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user03")
public class User03Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        String url = String.format("http://%s/user/get?id=%d", "demo-provider", id);
        return restTemplate.getForObject(url, UserDTO.class);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 请求体
        String body = JSON.toJSONString(addDTO);
        // 创建 HttpEntity 对象
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        // 执行请求
        String url = String.format("http://%s/user/add", "demo-provider");
        return restTemplate.postForObject(url, entity, Integer.class);
    }

}
