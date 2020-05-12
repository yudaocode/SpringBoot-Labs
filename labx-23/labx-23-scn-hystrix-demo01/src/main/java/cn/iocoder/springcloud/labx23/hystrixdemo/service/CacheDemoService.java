package cn.iocoder.springcloud.labx23.hystrixdemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheDemoService {

    private Logger logger = LoggerFactory.getLogger(CacheDemoService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    @CacheResult(cacheKeyMethod = "genGetUserCacheKey")
    public String getUser(Integer id) {
        logger.info("[getUser][准备调用 user-service 获取用户({})详情]", id);
        return restTemplate.getForEntity("http://127.0.0.1:18080/user/get?id=" + id, String.class).getBody();
    }

    @HystrixCommand
    @CacheRemove(commandKey = "getUser", cacheKeyMethod = "genGetUserCacheKey")
    public void updateUser(Integer id) {
        logger.info("[updateUser][更新用户({})详情]", id);
    }

    public String getUserFallback(Integer id, Throwable throwable) {
        logger.info("[getUserFallback][id({}) exception({})]", id, ExceptionUtils.getRootCauseMessage(throwable));
        return "mock:User:" + id;
    }

    public String genGetUserCacheKey(Integer id) {
        return "USER_" + id;
    }

}
