package com.lucifer.controller;

import com.alibaba.fastjson.JSON;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import com.lucifer.service.impl.OrderServiceImpl4;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
public class OderController4 {

    @Resource
    private OrderServiceImpl4 orderServiceImpl4;
    @Resource
    private ProductService productService;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //下单
    @GetMapping(value = "/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Long pid) {
        //调用商品微服务，查询商品信息
        Product product = productService.findById(pid);
        log.info("查询pid:{},的内容:{}", pid, JSON.toJSONString(product));

        //测试feign对sentinel的容错类
        if (product.getPid() < 0) {
            Order order = new Order();
            order.setOid(product.getPid());
            order.setPName("下单失败");
            return order;
        }

        //下单（创建订单）
        Order order = new Order();
        order.setUid(1L);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPName(product.getPName());
        order.setPrice(product.getPrice());
        order.setNumber(1L);
        orderServiceImpl4.createOrderBefore(order);
        log.info("创建订单成功，订单信息为:{}", JSON.toJSONString(order));

        //向rocketMQ中投递一个下单成功的消息
        //参数一：指定topic
        //参数二：指定消息体
        rocketMQTemplate.convertAndSend("order-topic",order);

        return order;
    }
}
