package cn.iocoder.springboot.lab22.validation.dto;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

/**
 * 用户更新状态 DTO
 */
public class UserUpdateStatusDTO {

    /**
     * 分组 01 ，要求状态必须为 true
     */
    public interface Group01 {}

    /**
     * 状态 02 ，要求状态必须为 false
     */
    public interface Group02 {}

    /**
     * 状态
     */
    @AssertTrue(message = "状态必须为 true", groups = Group01.class)
    @AssertFalse(message = "状态必须为 false", groups = Group02.class)
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public UserUpdateStatusDTO setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}
