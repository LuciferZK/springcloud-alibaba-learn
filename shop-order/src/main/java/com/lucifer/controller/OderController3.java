package com.lucifer.controller;

import com.lucifer.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


//@RestController
@Slf4j
public class OderController3 {

    @Resource
    private OrderServiceImpl3 orderServiceImpl3;

    //测试高并发
    @GetMapping(value = "/order/message1")
    public String message() {
       // orderServiceImpl3.message();
        return "message1";
    }

    //测试高并发
    @GetMapping(value = "/order/message2")
    public String message2() {
        /*orderServiceImpl3.message();
        return "message2";*/
        return orderServiceImpl3.message("lucifer");
    }
}
