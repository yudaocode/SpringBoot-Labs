package cn.iocoder.springboot.lab59.resillience4jdemo.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/time-limiter-demo")
public class TimeLimiterDemoController {

    @Autowired
    private TimeLimiterService timeLimiterService;

    @GetMapping("/get_user")
    public String getUser(@RequestParam("id") Integer id) throws ExecutionException, InterruptedException {
        return timeLimiterService.getUser0(id).get();
    }

    @Service
    public static class TimeLimiterService {

        private Logger logger = LoggerFactory.getLogger(TimeLimiterService.class);

        @Bulkhead(name = "backendD", type = Bulkhead.Type.THREADPOOL)
        @TimeLimiter(name = "backendF", fallbackMethod = "getUserFallback")
        public CompletableFuture<String> getUser0(Integer id) throws InterruptedException {
            logger.info("[getUser][id({})]", id);
            Thread.sleep(10 * 1000L); // sleep 10 秒
            return CompletableFuture.completedFuture("User:" + id);
        }

        public CompletableFuture<String> getUserFallback(Integer id, Throwable throwable) {
            logger.info("[getUserFallback][id({}) exception({})]", id, throwable.getClass().getSimpleName());
            return CompletableFuture.completedFuture("mock:User:" + id);
        }

    }

}
