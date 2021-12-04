package cn.iocoder.springboot.lab12.mybatis.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

public class UserDetailVO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码（明文）
     *
     * ps：生产环境下，千万不要明文噢
     */
    private String password;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;
    /**
     * 租户编号
     */
    private Integer tenantId;

    public Integer getId() {
        return id;
    }

    public UserDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserDetailVO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDetailVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public UserDetailVO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public UserDetailVO setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String toString() {
        return "UserDetailVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                ", tenantId=" + tenantId +
                '}';
    }
}
