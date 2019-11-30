package cn.iocoder.springboot.lab29.asynctask.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // 开启 @Async 的支持
public class AsyncConfig {

//    public static final String EXECUTOR_BEAN_NAME_01 = "executor-one";

    public static class ExecutorOneConfiguration implements AsyncConfigurer {

        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.task.execution-one") // 读取 spring.datasource.user 配置到 HikariDataSource 对象
        public TaskExecutionProperties taskExecutionPropertiesOne() {
            return new TaskExecutionProperties();
        }

//        @Bean(name = EXECUTOR_BEAN_NAME_01)
//        public ThreadPoolTaskExecutor threadPoolTaskExecutorOne() {
//            // 创建 TaskExecutorBuilder 对象
//            TaskExecutorBuilder builder = createTskExecutorBuilder(this.taskExecutionPropertiesOne());
//            // 创建 ThreadPoolTaskExecutor 对象
//            return builder.build();
//        }

        @Override
        public Executor getAsyncExecutor() {
            // 创建 TaskExecutorBuilder 对象
            TaskExecutorBuilder builder = createTskExecutorBuilder(this.taskExecutionPropertiesOne());
            // 创建 ThreadPoolTaskExecutor 对象
            ThreadPoolTaskExecutor executor = builder.build();
            // 初始化
            executor.initialize();
            // 返回
            return executor;
        }

    }

    private static TaskExecutorBuilder createTskExecutorBuilder(TaskExecutionProperties properties) {
        // Pool 属性
        TaskExecutionProperties.Pool pool = properties.getPool();
        TaskExecutorBuilder builder = new TaskExecutorBuilder();
        builder = builder.queueCapacity(pool.getQueueCapacity());
        builder = builder.corePoolSize(pool.getCoreSize());
        builder = builder.maxPoolSize(pool.getMaxSize());
        builder = builder.allowCoreThreadTimeOut(pool.isAllowCoreThreadTimeout());
        builder = builder.keepAlive(pool.getKeepAlive());
        // Shutdown 属性
        TaskExecutionProperties.Shutdown shutdown = properties.getShutdown();
        builder = builder.awaitTermination(shutdown.isAwaitTermination());
        builder = builder.awaitTerminationPeriod(shutdown.getAwaitTerminationPeriod());
        // 其它基本属性
        builder = builder.threadNamePrefix(properties.getThreadNamePrefix());
//        builder = builder.customizers(taskExecutorCustomizers.orderedStream()::iterator);
//        builder = builder.taskDecorator(taskDecorator.getIfUnique());
        return builder;
    }

}
