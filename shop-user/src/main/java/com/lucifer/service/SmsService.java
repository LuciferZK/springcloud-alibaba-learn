package com.lucifer.service;

import com.lucifer.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//consumerGroup 消费者组名 topic 要消费的主题
@RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic")
public class SmsService implements RocketMQListener<Order> {
    //消费逻辑
    @Override
    public void onMessage(Order order) {
        log.info("接收到了一个订单消息{}，接下来要发送短信通知了了",order);
    }
}
