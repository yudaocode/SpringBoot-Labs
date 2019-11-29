package cn.iocoder.springboot.lab29.asynctask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    public static void main(String[] args) {
        // 创建线程池。这里只是临时测试，不要扣艿艿遵守阿里 Java 开发规范，YEAH
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交任务到线程池中执行。
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("听说我被异步调用了");
            }

        });
    }

}
