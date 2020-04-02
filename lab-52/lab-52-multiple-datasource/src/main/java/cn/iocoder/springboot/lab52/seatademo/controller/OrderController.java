package cn.iocoder.springboot.lab52.seatademo.controller;

import cn.iocoder.springboot.lab52.seatademo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Integer createOrder(@RequestParam("userId") Long userId,
                               @RequestParam("productId") Long productId,
                               @RequestParam("price") Integer price) throws Exception {
        logger.info("[createOrder] 收到下单请求,用户:{}, 商品:{}, 价格:{}", userId, productId, price);
        return orderService.createOrder(userId, productId, price);
    }

}
