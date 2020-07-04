package cn.iocoder.springcloudalibaba.labx20.listenerdemo.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 用户注册事件
 */
public class UserRegisterEvent extends RemoteApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent() { // 序列化
    }

    public UserRegisterEvent(Object source, String originService, String destinationService, String username) {
        super(source, originService);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
