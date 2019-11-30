package cn.iocoder.springboot.lab29.asynctask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    public void task01() {
//        long now = System.currentTimeMillis();
//        logger.info("[task01][开始执行]");
//
//        execute01();
//        execute02();
//
//        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
//    }
//
//    public void task02() {
//        long now = System.currentTimeMillis();
//        logger.info("[task02][开始执行]");
//
//        execute01Async();
//        execute02Async();
//
//        logger.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
//    }
//
//    public void task03() throws ExecutionException, InterruptedException {
//        long now = System.currentTimeMillis();
//        logger.info("[task03][开始执行]");
//
//        // 执行任务
//        Future<Integer> execute01Result = execute01AsyncWithFuture();
//        Future<Integer> execute02Result = execute02AsyncWithFuture();
//        // 阻塞等待结果
//        execute01Result.get();
//        execute02Result.get();
//
//        logger.info("[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
//    }

    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }

    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

    public Integer execute01() {
        logger.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        logger.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public Integer zhaoDaoNvPengYou(Integer a, Integer b) {
        throw new RuntimeException("程序员不需要女朋友");
    }

}
