package cn.iocoder.springcloud.labx08.gatewaydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GatewayPropertiesRefresher implements ApplicationListener<EnvironmentChangeEvent>,
        ApplicationContextAware, ApplicationEventPublisherAware, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(GatewayPropertiesRefresher.class);

    private static final String ID_PATTERN = "spring.cloud.gateway.routes";

    private static final String DEFAULT_FILTER_PATTERN = "spring.cloud.gateway.default-filters";

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher publisher;

    private Environment environment;

    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent changeEvent) {
        if (true) {
            return;
        }
        // <0> 只处理 `spring.cloud.gateway` 配置项的变更
        if (!isGatewayConfigChanged(changeEvent)) {
            return;
        }
        // <1>
        preDestroyGatewayProperties(changeEvent);
        // <2> 貌似不用添加。添加了就死循环了。
//        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.getKeys()));
        // <3>
//        refreshGatewayRouteDefinition();
    }

    private boolean isGatewayConfigChanged(EnvironmentChangeEvent changeEvent) {
        for (String key : changeEvent.getKeys()) {
            if (key.contains(ID_PATTERN)
                || key.contains(DEFAULT_FILTER_PATTERN)) {
                return true;
            }
        }
        return false;
    }

    private synchronized void preDestroyGatewayProperties(EnvironmentChangeEvent changeEvent) {
        logger.info("Pre Destroy GatewayProperties!");
        // 判断 `spring.cloud.gateway.routes` 配置项，是否被全部删除。如果是，则置空 GatewayProperties 的 `routes` 属性
        final boolean needClearRoutes = this.checkNeedClear(ID_PATTERN);
        if (needClearRoutes) {
            this.gatewayProperties.setRoutes(new ArrayList<>());
        }
        // 判断 `spring.cloud.gateway.default-filters` 配置项，是否被全部删除。如果是，则置空 GatewayProperties 的 `defaultFilters` 属性
        final boolean needClearDefaultFilters = this.checkNeedClear(DEFAULT_FILTER_PATTERN);
        if (needClearDefaultFilters) {
            this.gatewayProperties.setRoutes(new ArrayList<>());
        }
        logger.info("Pre Destroy GatewayProperties finished!");
    }

    private void refreshGatewayRouteDefinition() {
        logger.info("Refreshing Gateway RouteDefinition!");
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        logger.info("Gateway RouteDefinition refreshed!");
    }

    // 判断是否清除的标准，判断指定属性是否为空。如果是，则说明被清空了。
    private boolean checkNeedClear(String pattern) {
        return environment.getProperty(pattern) == null;
    }

}
