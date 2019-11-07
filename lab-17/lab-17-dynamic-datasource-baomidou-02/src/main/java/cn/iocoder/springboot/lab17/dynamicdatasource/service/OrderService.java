package cn.iocoder.springboot.lab17.dynamicdatasource.service;

import cn.iocoder.springboot.lab17.dynamicdatasource.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;



}
