package cn.iocoder.springboot.lab34.actuatordemo.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class DemoHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        // 判断是否健康
        boolean success = checkSuccess();

        // 如果健康，则标记状态为 UP
        if (success) {
            builder.up().build();
            return;
        }

        // 如果不健康，则标记状态为 DOWN
        builder.down().withDetail("msg", "我就是做个示例而已");
    }

    private boolean checkSuccess() {
        return false;
    }

}
