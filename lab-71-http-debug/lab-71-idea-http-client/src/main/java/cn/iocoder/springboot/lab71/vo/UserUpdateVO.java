package cn.iocoder.springboot.lab71.vo;

/**
 * 用户更新 VO
 */
public class UserUpdateVO {

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

    public UserUpdateVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserUpdateVO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "UserUpdateVO{" +
                "nickname='" + nickname + '\'' +
                ", gender=" + gender +
                '}';
    }

}
