package cn.iocoder.springboot.labs.lab10.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ServerLifeCycleListener implements ApplicationListener<ApplicationEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ServerLifeCycleHealthIndicator healthIndicator;

    public ServerLifeCycleListener(ServerLifeCycleHealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
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
        healthIndicator.up();
    }

    @SuppressWarnings("unused")
    private void handleApplicationFailedEvent(ApplicationFailedEvent event) {
        healthIndicator.down();
    }

    @SuppressWarnings("unused")
    private void handleContextClosedEvent(ContextClosedEvent event) {
        // 标记不提供服务
        healthIndicator.outOfService();

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
