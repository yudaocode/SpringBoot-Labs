package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户 DO
 */
@Document(collection = "User")
public class UserDO {

    /**
     * 用户信息
     */
    public static class Profile {

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

        public Profile setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Integer getGender() {
            return gender;
        }

        public Profile setGender(Integer gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public String toString() {
            return "Profile{" +
                    "nickname='" + nickname + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    @Id
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户信息
     */
    private Profile profile;

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public UserDO setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", profile=" + profile +
                '}';
    }

}
