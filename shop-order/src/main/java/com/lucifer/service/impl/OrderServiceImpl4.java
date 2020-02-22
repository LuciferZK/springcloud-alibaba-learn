package com.lucifer.service.impl;

import com.lucifer.dao.OrderDao;
import com.lucifer.dao.TxLogDao;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.TxLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl4 {

    @Resource
    private OrderDao orderDao;
    @Resource
    private TxLogDao txLogDao;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderBefore(Order order) {

        String txId= UUID.randomUUID().toString();

        //发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producter_group",
                "tx_topic",
                MessageBuilder.withPayload(order).
                        setHeader("txId",txId).build(),
                order
        );
    }

    //下单
    @Transactional
    public void createOrder(String txId,Order order) {
        //保存订单
        orderDao.insert(order);

        TxLog txLog=new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());
        //记录事务日志
        txLogDao.insert(txLog);
    }

}




