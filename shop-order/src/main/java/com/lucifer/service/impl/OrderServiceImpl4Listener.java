package com.lucifer.service.impl;

import com.lucifer.dao.TxLogDao;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RocketMQTransactionListener(txProducerGroup = "tx_producter_group")
public class OrderServiceImpl4Listener implements RocketMQLocalTransactionListener {

    @Resource
    private OrderServiceImpl4 orderServiceImpl4;
    @Resource
    private TxLogDao txLogDao;

    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String txId=(String)message.getHeaders().get("txId");

        try {
            //下单
            orderServiceImpl4.createOrder(txId,(Order) o);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //消息回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String txId=(String)message.getHeaders().get("txId");
        TxLog txLog = txLogDao.selectById(txId);
        if(txLog!=null){
            //本地事务（订单）成功了
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
