package cn.iocoder.springboot.lab12.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户拓展 DO
 */
@TableName(value = "user_profile")
public class UserProfileDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 性别
     */
    private Integer gender;
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

    public UserProfileDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserProfileDO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public UserProfileDO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public UserProfileDO setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserProfileDO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
