package cn.iocoder.springcloud.labx21.zuuldemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ZuulRouteRefreshListener implements ApplicationListener<EnvironmentChangeEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ZuulRouteRefreshListener.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RouteLocator routeLocator;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        // 判断是否有 `zuul.` 配置变化
        boolean zuulConfigUpdated = false;
        for (String key : event.getKeys()) {
            if (key.startsWith("zuul.")) {
                zuulConfigUpdated = true;
                break;
            }
        }
        if (!zuulConfigUpdated) {
            return;
        }

        // 发布 RoutesRefreshedEvent 事件
        this.publisher.publishEvent(new RoutesRefreshedEvent(routeLocator));
        logger.info("发布 RoutesRefreshedEvent 事件完成，刷新 Zuul 路由");
    }

}
