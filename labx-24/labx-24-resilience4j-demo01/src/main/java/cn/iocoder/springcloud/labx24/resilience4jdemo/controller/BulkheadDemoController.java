package cn.iocoder.springcloud.labx24.resilience4jdemo.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulkhead-demo")
public class BulkheadDemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get_user")
    @Bulkhead(name = "backendC", fallbackMethod = "getUserFallback", type = Bulkhead.Type.SEMAPHORE)
    public String getUser(@RequestParam("id") Integer id) throws InterruptedException {
        logger.info("[getUser][id({})]", id);
        Thread.sleep(10 * 1000L); // sleep 10 秒
        return "User:" + id;
    }

    public String getUserFallback(Integer id, Throwable throwable) {
        logger.info("[getUserFallback][id({}) exception({})]", id, throwable.getClass().getSimpleName());
        return "mock:User:" + id;
    }

}
