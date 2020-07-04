package cn.iocoder.springboot.lab52.seatademo.service.impl;

import cn.iocoder.springboot.lab52.seatademo.dao.OrderDao;
import cn.iocoder.springboot.lab52.seatademo.entity.OrderDO;
import cn.iocoder.springboot.lab52.seatademo.service.OrderService;
import cn.iocoder.springboot.lab52.seatademo.service.AccountService;
import cn.iocoder.springboot.lab52.seatademo.service.ProductService;
import com.baomidou.dynamic.datasource.annotation.DS;
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
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Override
    @DS(value = "order-ds")
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
