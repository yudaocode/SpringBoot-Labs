package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class UserProviderRibbonClientConfiguration {

    @Bean
    @Primary
    public IRule ribbonCustomRule() {
        return new RandomRule();
    }

}
