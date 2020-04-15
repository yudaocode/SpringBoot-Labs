package cn.iocoder.springboot.lab54.eventdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户注册事件
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
