package cn.iocoder.springcloud.labx03.feigndemo.provider;

import cn.iocoder.springcloud.labx03.feigndemo.provider.api.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
    }

    @RestController
    static class TestController implements TestService {

        private Logger logger = LoggerFactory.getLogger(TestController.class);

        @Value("${server.port}")
        private Integer serverPort;

        public String echo(String name) {
            // 模拟执行 100ms 时长。方便后续我们测试请求超时
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 记录被调用的日志
            logger.info("[echo][被调用啦 name({})]", name);

            return serverPort + "-provider:" + name;
        }

//        @GetMapping("/echo")
//        public String echo(String name) throws InterruptedException {
//            // 模拟执行 100ms 时长。方便后续我们测试请求超时
//            Thread.sleep(100L);
//
//            // 记录被调用的日志
//            logger.info("[echo][被调用啦 name({})]", name);
//
//            return serverPort + "-provider:" + name;
//        }

    }

}
