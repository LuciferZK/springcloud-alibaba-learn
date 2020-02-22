package com.lucifer.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

//发送消息
public class RocketMQSendMessageTest {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //1.创建消息生产者，并且设置生产组名
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("my-group");
        //2.为生产者设置NameServer的地址
        defaultMQProducer.setNamesrvAddr("192.168.160.130:9876");
        //3.启动生产者
        defaultMQProducer.start();
        //4.构建消息对象，主要是这只消息的主题 标签 内容
        Message message=new Message("my-topic","my-tag","Hello,rocketMQ message".getBytes());
        //5.发送消息
        SendResult result = defaultMQProducer.send(message, 10000);
        System.out.println("result："+result);
        //6.关闭生产者
        defaultMQProducer.shutdown();

    }


}
