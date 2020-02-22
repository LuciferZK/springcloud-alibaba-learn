package com.lucifer.test;

import com.lucifer.OrderApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //同步方法
    @Test
    public void testSyncSend() {
        //参数一：topic
        //参数二：消息体
        //参数三：超时时间
        SendResult result = rocketMQTemplate.syncSend("test-topic-1:tag1", "这是一条同步消息", 10000);
        log.info("result:{}", result);
    }

    //异步方法
    @Test
    public void testAsyncSend() throws InterruptedException {
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {
           //成功响应回调
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }
            //异常响应回调
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("===============================================");
        TimeUnit.SECONDS.sleep(100000);  //休眠一段时间，让rocketMQ有足够的时间回调，否则sendResult获取不到
    }

    @Test
    public void oneWaySend(){
        rocketMQTemplate.sendOneWay("test-topic-1","这是一条单向消息");
    }


}
