package com.lucifer.service;

import com.aliyuncs.CommonResponse;
import com.lucifer.dao.UserDao;
import com.lucifer.pojo.Order;
import com.lucifer.pojo.User;
import com.lucifer.util.SmsEntity;
import com.lucifer.util.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = "shop-user",  //consumerGroup 消费者组名
        topic = "order-topic",  // topic 要消费的主题
        consumeMode = ConsumeMode.CONCURRENTLY, //消费模式，指定是否顺序消费 CONCURRENTLY(同步，默认) ORDERLY(顺序)
        messageModel = MessageModel.CLUSTERING //消费模式，BROADCASTING(广播) CLUSTERING(集群)
)
public class SmsService implements RocketMQListener<Order> {

    @Resource
    private UserDao userDao;

    //消费逻辑
    @Override
    public void onMessage(Order order) {
        log.info("接收到了一个订单消息{}，接下来要发送短信通知了了", order);

        Long uid = order.getUid();
        User user = userDao.selectById(uid);
        SmsEntity smsEntity = new SmsEntity();
        smsEntity.setPhoneNumbers(user.getTelephone());
        smsEntity.setSignName("飞猫旅游网");
        smsEntity.setTemplateCode("SMS_149100474");
        CommonResponse commonResponse = SmsUtils.SendSms(smsEntity);
        log.info("commonResponse:{}",commonResponse);
    }
}
