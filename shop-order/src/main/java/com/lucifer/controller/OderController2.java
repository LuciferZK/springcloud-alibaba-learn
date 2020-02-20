package com.lucifer.controller;

import com.alibaba.fastjson.JSON;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.Product;
import com.lucifer.service.OrderService;
import com.lucifer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//@RestController
@Slf4j
public class OderController2 {

    @Resource
    private ProductService productService;

    //下单
    @GetMapping(value = "/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Long pid) throws InterruptedException {
        //调用商品微服务，查询商品信息
        Product product = productService.findById(pid);
        //模拟需要2秒钟才能调用成功
        TimeUnit.SECONDS.sleep(2);
        log.info("查询pid:{},的内容:{}", pid, JSON.toJSONString(product));

        //下单（创建订单）
        Order order = new Order();
        order.setUid(1L);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPName(product.getPName());
        order.setPrice(product.getPrice());
        order.setNumber(1L);
        //为了不产生大量垃圾数据，暂时注释掉
        //orderService.createOrder(order);
        log.info("创建订单成功，订单信息为:{}", JSON.toJSONString(order));
        return order;
    }

    //测试高并发
    @GetMapping(value = "/order/message")
    public String message(){
        return "测试高并发";
    }


}
