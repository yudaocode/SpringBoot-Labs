package cn.iocoder.springboot.lab40.jenkinsdemo.actuate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ServerHealthIndicator extends AbstractHealthIndicator implements ApplicationListener<ApplicationEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 是否在服务中
     */
    private volatile boolean inService = false;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        if (inService) {
            builder.up();
        } else {
            builder.down();
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            this.handleApplicationReadyEvent((ApplicationReadyEvent) event);
        } else if (event instanceof ApplicationFailedEvent) {
            this.handleApplicationFailedEvent((ApplicationFailedEvent) event);
        } else if (event instanceof ContextClosedEvent) {
            this.handleContextClosedEvent((ContextClosedEvent) event);
        }
    }

    @SuppressWarnings("unused")
    private void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        this.inService = true;
    }

    @SuppressWarnings("unused")
    private void handleApplicationFailedEvent(ApplicationFailedEvent event) {
        this.inService = false;
    }

    @SuppressWarnings("unused")
    private void handleContextClosedEvent(ContextClosedEvent event) {
        // 标记不提供服务
        this.inService = false;

        // sleep 等待负载均衡完成健康检查
        for (int i = 0; i < 20; i++) { // TODO 20 需要配置
            logger.info("[handleContextClosedEvent][优雅关闭，第 {} sleep 等待负载均衡完成健康检查]", i);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignore) {
            }
        }
    }

}
