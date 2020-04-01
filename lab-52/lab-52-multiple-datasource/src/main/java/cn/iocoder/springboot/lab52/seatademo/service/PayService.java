package cn.iocoder.springboot.lab52.seatademo.service;

/**
 * 支付 Service
 */
public interface PayService {

    /**
     * 扣除余额
     *
     * @param userId 用户编号
     * @param price  扣减金额
     * @throws Exception 失败时抛出异常
     */
    void reduceBalance(Long userId, Integer price) throws Exception;

}
