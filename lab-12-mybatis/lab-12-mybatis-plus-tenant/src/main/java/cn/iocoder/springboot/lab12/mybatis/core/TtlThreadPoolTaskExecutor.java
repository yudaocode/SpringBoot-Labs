package cn.iocoder.springboot.lab12.mybatis.core;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Deprecated
public class TtlThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        super.execute(Objects.requireNonNull(TtlRunnable.get(task)));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(Objects.requireNonNull(TtlRunnable.get(task)), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(Objects.requireNonNull(TtlRunnable.get(task)));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(Objects.requireNonNull(TtlCallable.get(task)));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(Objects.requireNonNull(TtlRunnable.get(task)));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(Objects.requireNonNull(TtlCallable.get(task)));
    }
}
