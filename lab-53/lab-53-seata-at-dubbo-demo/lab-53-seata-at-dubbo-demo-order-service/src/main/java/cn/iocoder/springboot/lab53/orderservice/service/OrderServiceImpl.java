package cn.iocoder.springboot.lab53.orderservice.service;

import cn.iocoder.springboot.lab53.accountservice.api.AccountService;
import cn.iocoder.springboot.lab53.orderservice.api.OrderService;
import cn.iocoder.springboot.lab53.orderservice.dao.OrderDao;
import cn.iocoder.springboot.lab53.orderservice.entity.OrderDO;
import cn.iocoder.springboot.lab53.productservice.api.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.apache.dubbo.config.annotation.Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Reference
    private AccountService accountService;
    @Reference
    private ProductService productService;

    @Override
    @GlobalTransactional
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        productService.reduceStock(productId, amount);

        // 扣减余额
        accountService.reduceBalance(userId, price);

        // 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

}
