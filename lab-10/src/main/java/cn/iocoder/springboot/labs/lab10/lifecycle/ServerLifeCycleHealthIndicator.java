package cn.iocoder.springboot.labs.lab10.lifecycle;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

public class ServerLifeCycleHealthIndicator extends AbstractHealthIndicator {

    /**
     * 服务状态
     *
     * 启动阶段：
     *   1. 项目初始启动时，状态为 OUT_OF_SERVICE 不提供服务。
     *   2. 服务启动完成（ApplicationReadyEvent）时，状态为 UP 启动。
     *   3. 服务启动失败（ApplicationFailedEvent）时，状态 DOWN 关闭。
     *
     * 关闭阶段：
     *   1. 服务开始关闭（ContextClosedEvent）时，状态为 OUT_OF_SERVICE 不提供服务。
     *   2. 因为服务关闭完成，不存在事件，所以暂时不处理。
     *
     * 具体的状态变更，通过
     */
    private volatile Status status = Status.OUT_OF_SERVICE;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        builder.status(status);
    }

    public void up() {
        this.status = Status.UP;
    }

    public void down() {
        this.status = Status.DOWN;
    }

    public void outOfService() {
        this.status = Status.OUT_OF_SERVICE;
    }

    public Status status() {
        return this.status;
    }

}
