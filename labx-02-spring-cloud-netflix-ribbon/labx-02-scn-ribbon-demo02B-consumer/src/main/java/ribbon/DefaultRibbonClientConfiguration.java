package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRibbonClientConfiguration {

    @Bean
    public IRule ribbonDefaultRule() {
        return new RoundRobinRule();
    }

}
