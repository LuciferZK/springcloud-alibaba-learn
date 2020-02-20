package com.lucifer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class OderController3 {

    //测试高并发
    @GetMapping(value = "/order/message1")
    public String message() {
        return "message1";
    }

    //测试高并发
    @GetMapping(value = "/order/message2")
    public String message2() {
        return "message2";
    }
}
