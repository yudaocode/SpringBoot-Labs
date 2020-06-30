package cn.iocoder.springcloud.labx23.hystrixdemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CollapserDemoService {

    private Logger logger = LoggerFactory.getLogger(CollapserDemoService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(
            batchMethod = "getUsers",
            collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "10000") // 演示，所以设置的时间较长
            }
    )
    public Future<String> getUserFuture(Integer id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    public List<String> getUsers(List<Integer> ids) {
        logger.info("[getUsers][准备调用 user-service 获取多个用户({})详情]", ids);
        String[] users = restTemplate.getForEntity("http://127.0.0.1:18080/user/batch_get?ids=" + StringUtils.join(ids, ',')
                , String[].class).getBody();
        return users == null || users.length == 0 ? Collections.emptyList() : Arrays.asList(users);
    }

}
