package cn.iocoder.springboot.lab29.asynctask.config;

import cn.iocoder.springboot.lab29.asynctask.core.async.GlobalAsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // 开启 @Async 的支持
public class AsyncConfig implements AsyncConfigurer {

    @Autowired
    private GlobalAsyncExceptionHandler exceptionHandler;

    @Override
    public Executor getAsyncExecutor() {
        return null;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return exceptionHandler;
    }

}
