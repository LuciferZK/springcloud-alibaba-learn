package com.lucifer.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

//OrderServiceImpl3对应的BlockException处理的类
@Slf4j
public class OrderServiceImpl3BlockHandler {

    //blockHandler
    //要求
    //1.当前方法的返回值和参数要求跟原方法一致（此处为public String message(String name){}）
    //2.但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public static String blockHandler(String name, BlockException e) {
        //自定义异常处理逻辑
        log.error("触发了BlockException,内容为{}", e);
        return "BlockException";
    }

}
