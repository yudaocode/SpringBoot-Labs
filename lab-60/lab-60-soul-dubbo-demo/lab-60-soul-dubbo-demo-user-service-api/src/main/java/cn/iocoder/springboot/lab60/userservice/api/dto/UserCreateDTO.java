package cn.iocoder.springboot.lab60.userservice.api.dto;

/**
 * 用户创建 DTO
 */
public class UserCreateDTO {

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private Integer gender;

    public String getNickname() {
        return nickname;
    }

    public UserCreateDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserCreateDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
