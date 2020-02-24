package com.lucifer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@RestController
//@RefreshScope //只需要在需要动态读取配置的类上添加这个注解即可
public class NacosConfigController {

    @Resource
    private ConfigurableApplicationContext configurableApplicationContext;

    @Value("${config.appName}")
    private String appName;

    @Value("${config.env}")
    private String env;

    @GetMapping("/test-config1")
    public String testConfig1() {
        return configurableApplicationContext.getEnvironment().getProperty("config.appName");
    }

    //注解方式
    @GetMapping("/test-config2")
    public String testConfig2() {
        return appName;
    }

    @GetMapping("/test-config3")
    public String testConfig3() {
        return env;
    }



}
