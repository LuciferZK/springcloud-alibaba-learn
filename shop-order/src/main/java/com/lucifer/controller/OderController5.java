package com.lucifer.controller;


import com.lucifer.pojo.Order;
import com.lucifer.service.impl.OrderServiceImpl5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
public class OderController5 {

    @Resource
    private OrderServiceImpl5 orderServiceImpl5;

    //下单
    @GetMapping(value = "/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Long pid) {
        return orderServiceImpl5.createOrder(pid);
    }
}
