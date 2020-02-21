package com.lucifer.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl3 {

    int i = 0;

    //定义一个资源
    //定义资源 value指定资源名称
    //blockHandler 定义当资源内部发生了BlockException该进入的方法【捕获的是sentinel定义的异常】
    //fallback  定义当资源内部发生了Throwable应该进入的方法
    @SentinelResource(value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderServiceImpl3Fallback.class,
            fallback = "fallback")
    public String message(String name) {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message";
    }


}

/*
    int i = 0;
    //定义一个资源
    //定义资源 value指定资源名称
    //blockHandler 定义当资源内部发生了BlockException该进入的方法【捕获的是sentinel定义的异常】
    //fallback  定义当资源内部发生了Throwable应该进入的方法
    @SentinelResource(value = "message", blockHandler = "blockHandler", fallback = "fallback")
    public String message(String name) {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message";
    }

    //blockHandler
    //要求
    //1.当前方法的返回值和参数要求跟原方法一致（此处为public String message(String name){}）
    //2.但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public String blockHandler(String name, BlockException e) {
        //自定义异常处理逻辑
        log.error("触发了BlockException,内容为{}", e);
        return "BlockException";
    }

    //fallback
    //要求
    //1.当前方法的返回值和参数要求跟原方法一致（此处为public String message(String name){}）
    //2.但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public String fallback(String name, Throwable e) {
        //自定义异常处理逻辑
        log.error("触发了Throwable,内容为{}", e);
        return "Throwable";
    }

}
    */
/**
 * Throwable 异常比BlockException大。blockHandler捕获到异常，不会进去fallback
 * 类似try{}catch(){}
 */





