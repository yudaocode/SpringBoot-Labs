package cn.iocoder.springcloud.labx17.orderservice.service.impl;

import cn.iocoder.springcloud.labx17.orderservice.dao.OrderDao;
import cn.iocoder.springcloud.labx17.orderservice.entity.OrderDO;
import cn.iocoder.springcloud.labx17.orderservice.feign.*;
import cn.iocoder.springcloud.labx17.orderservice.feign.dto.*;
import cn.iocoder.springcloud.labx17.orderservice.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountServiceFeignClient accountService;
    @Autowired
    private ProductServiceFeignClient productService;

    @Override
    @GlobalTransactional
    public Integer createOrder(Long userId, Long productId, Integer price) {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        productService.reduceStock(new ProductReduceStockDTO().setProductId(productId).setAmount(amount));

        // 扣减余额
        accountService.reduceBalance(new AccountReduceBalanceDTO().setUserId(userId).setPrice(price));

        // 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

}
