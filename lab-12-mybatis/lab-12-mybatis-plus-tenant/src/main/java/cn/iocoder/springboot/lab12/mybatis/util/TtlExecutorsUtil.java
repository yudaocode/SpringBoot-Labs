package cn.iocoder.springboot.lab12.mybatis.util;

import com.alibaba.ttl.spi.TtlEnhanced;
import com.alibaba.ttl.threadpool.agent.TtlAgent;
import org.springframework.lang.Nullable;

import java.util.concurrent.Executor;

/**
 * {@link com.alibaba.ttl.threadpool.TtlExecutors} 工具类
 */
@Deprecated
public class TtlExecutorsUtil {

    public static Executor getTtlThreadPoolTaskExecutor(@Nullable Executor executor) {
        if (TtlAgent.isTtlAgentLoaded() || null == executor || executor instanceof TtlEnhanced) {
            return executor;
        }
//        return new ExecutorTtlWrapper(executor, true);
        return null;
    }

}
