package cn.iocoder.springboot.lab29.asynctask.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // 开启 @Async 的支持
public class AsyncConfig {

    public static final String EXECUTOR_ONE_BEAN_NAME = "executor-one";
    public static final String EXECUTOR_TWO_BEAN_NAME = "executor-two";

    @Configuration
    public static class ExecutorOneConfiguration {

        @Bean(name = EXECUTOR_ONE_BEAN_NAME + "-properties")
        @Primary
        @ConfigurationProperties(prefix = "spring.task.execution-one") // 读取 spring.task.execution-one 配置到 TaskExecutionProperties 对象
        public TaskExecutionProperties taskExecutionProperties() {
            return new TaskExecutionProperties();
        }

        @Bean(name = EXECUTOR_ONE_BEAN_NAME)
        public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
            // 创建 TaskExecutorBuilder 对象
            TaskExecutorBuilder builder = createTskExecutorBuilder(this.taskExecutionProperties());
            // 创建 ThreadPoolTaskExecutor 对象
            return builder.build();
        }

    }

    @Configuration
    public static class ExecutorTwoConfiguration {

        @Bean(name = EXECUTOR_TWO_BEAN_NAME + "-properties")
        @ConfigurationProperties(prefix = "spring.task.execution-two") // 读取 spring.task.execution-two 配置到 TaskExecutionProperties 对象
        public TaskExecutionProperties taskExecutionProperties() {
            return new TaskExecutionProperties();
        }

        @Bean(name = EXECUTOR_TWO_BEAN_NAME)
        public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
            // 创建 TaskExecutorBuilder 对象
            TaskExecutorBuilder builder = createTskExecutorBuilder(this.taskExecutionProperties());
            // 创建 ThreadPoolTaskExecutor 对象
            return builder.build();
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
