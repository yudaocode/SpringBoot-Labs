package cn.iocoder.springcloud.labx17.accountservice.dto;

/**
 * 账户减少余额 DTO
 */
public class AccountReduceBalanceDTO {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 扣减金额
     */
    private Integer price;

    public Long getUserId() {
        return userId;
    }

    public AccountReduceBalanceDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AccountReduceBalanceDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

}
