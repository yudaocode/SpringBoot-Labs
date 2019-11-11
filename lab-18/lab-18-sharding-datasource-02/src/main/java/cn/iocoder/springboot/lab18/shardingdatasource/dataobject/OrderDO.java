package cn.iocoder.springboot.lab18.shardingdatasource.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单 DO
 */
@TableName(value = "orders")
public class OrderDO {

    /**
     * 订单编号
     */
    private Long id;
    /**
     * 用户编号
     */
    private Integer userId;

    public Long getId() {
        return id;
    }

    public OrderDO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderDO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

}
