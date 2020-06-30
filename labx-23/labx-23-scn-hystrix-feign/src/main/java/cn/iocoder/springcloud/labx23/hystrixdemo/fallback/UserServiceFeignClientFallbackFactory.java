package cn.iocoder.springcloud.labx23.hystrixdemo.fallback;

import cn.iocoder.springcloud.labx23.hystrixdemo.feign.UserServiceFeignClient;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFeignClientFallbackFactory implements FallbackFactory<UserServiceFeignClient> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserServiceFeignClient create(Throwable cause) {
        return new UserServiceFeignClient() {

            @Override
            public String getUser(Integer id) {
                logger.info("[getUserFallback][id({}) exception({})]", id, ExceptionUtils.getRootCauseMessage(cause));
                return "mock:User:" + id;
            }

        };
    }

}
