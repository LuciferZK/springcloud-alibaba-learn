package com.lucifer.service.impl;

import com.lucifer.dao.OrderDao;
import com.lucifer.pojo.Order;
import com.lucifer.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public void createOrder(Order order) {
        orderDao.insert(order);
    }
}
