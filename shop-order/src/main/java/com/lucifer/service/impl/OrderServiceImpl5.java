package com.lucifer.service.impl;

import com.alibaba.fastjson.JSON;
import com.lucifer.dao.OrderDao;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.Product;
import com.lucifer.service.OrderService;
import com.lucifer.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderServiceImpl5 {

    @Resource
    private OrderDao orderDao;
    @Resource
    private ProductService productService;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GlobalTransactional //全局事务控制
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(Long pid) {

        //1.调用商品微服务，查询商品信息
        Product product = productService.findById(pid);
        log.info("查询pid:{},的内容:{}", pid, JSON.toJSONString(product));


        //2.下单（创建订单）
        Order order = new Order();
        order.setUid(1L);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPName(product.getPName());
        order.setPrice(product.getPrice());
        order.setNumber(1);
        orderDao.insert(order);
        log.info("创建订单成功，订单信息为:{}", JSON.toJSONString(order));

        //3.减少库存
        productService.reduceInventory(pid,order.getNumber());


        //4.向rocketMQ中投递一个下单成功的消息
        //参数一：指定topic
        //参数二：指定消息体
        //rocketMQTemplate.convertAndSend("order-topic",order);

        return order;

    }
}
