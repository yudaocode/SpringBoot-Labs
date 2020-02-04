package cn.iocoder.springboot.lab49.lombokdemo.dataobject;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户 DO
 */
@Setter
@Getter
public class UserDO {

    private String username;
    private String password;

}
