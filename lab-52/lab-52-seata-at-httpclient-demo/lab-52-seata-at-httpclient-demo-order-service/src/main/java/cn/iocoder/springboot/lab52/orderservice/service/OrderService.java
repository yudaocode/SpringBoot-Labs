package cn.iocoder.springboot.lab52.orderservice.service;

/**
 * 订单 Service
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param userId 用户编号
     * @param productId 产品编号
     * @param price 价格
     * @return 订单编号
     * @throws Exception 创建订单失败，抛出异常
     */
    Integer createOrder(Long userId, Long productId, Integer price) throws Exception;

}
