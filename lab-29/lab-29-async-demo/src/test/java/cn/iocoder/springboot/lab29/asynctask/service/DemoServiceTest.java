package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;

    @Test
    public void task01() {
        long now = System.currentTimeMillis();
        logger.info("[task01][开始执行]");

        demoService.execute01();
        demoService.execute02();

        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task02() {
        long now = System.currentTimeMillis();
        logger.info("[task02][开始执行]");

        demoService.execute01Async();
        demoService.execute02Async();

        logger.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task03() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task03][开始执行]");

        // 执行任务
        Future<Integer> execute01Result = demoService.execute01AsyncWithFuture();
        Future<Integer> execute02Result = demoService.execute02AsyncWithFuture();
        // 阻塞等待结果
        execute01Result.get();
        execute02Result.get();

        logger.info("[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task04() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task04][开始执行]");

        // 执行任务
        ListenableFuture<Integer> execute01Result = demoService.execute01AsyncWithListenableFuture();
        logger.info("[task04][execute01Result 的类型是：({})]",execute01Result.getClass().getSimpleName());
        execute01Result.addCallback(new SuccessCallback<Integer>() { // 增加成功的回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

        }, new FailureCallback() { // 增加失败的回调

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        execute01Result.addCallback(new ListenableFutureCallback<Integer>() { // 增加成功和失败的统一回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        // 阻塞等待结果
        execute01Result.get();

        logger.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void testZhaoDaoNvPengYou() throws InterruptedException {
        demoService.zhaoDaoNvPengYou(1, 2);

        // sleep 1 秒，保证异步调用的执行
        Thread.sleep(1000);
    }

}
