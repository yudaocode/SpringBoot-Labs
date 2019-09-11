package cn.iocoder.springboot.labs.lab10;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

//@Component
public class UserHealthIndicator implements HealthIndicator {

    /**
     * user监控 访问: http://localhost:8088/health
     *
     * @return 自定义Health监控
     */
    @Override
    public Health health() {
        return new Health.Builder().withDetail("usercount", 10) //自定义监控内容
                .withDetail("userstatus", "up").down().build();
    }

}
