package cn.iocoder.springcloud.labx21.zuuldemo;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 由 https://github.com/ctripcorp/apollo-use-cases/blob/master/spring-cloud-zuul 提供代码，感谢~
 */
@Component
public class ZuulPropertiesRefresher {

    private static final Logger logger = LoggerFactory.getLogger(ZuulPropertiesRefresher.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RouteLocator routeLocator;

    @ApolloConfigChangeListener(interestedKeyPrefixes = "zuul.")
    public void onChange(ConfigChangeEvent changeEvent) {
        refreshZuulProperties(changeEvent);
    }

    private void refreshZuulProperties(ConfigChangeEvent changeEvent) {
        logger.info("Refreshing zuul properties!");

        /*
         * rebind configuration beans, e.g. ZuulProperties
         * @see org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder#onApplicationEvent
         */
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));

        /*
         * refresh routes
         * @see org.springframework.cloud.netflix.zuul.ZuulServerAutoConfiguration.ZuulRefreshListener#onApplicationEvent
         */
        this.applicationContext.publishEvent(new RoutesRefreshedEvent(routeLocator));

        logger.info("Zuul properties refreshed!");
    }

}
