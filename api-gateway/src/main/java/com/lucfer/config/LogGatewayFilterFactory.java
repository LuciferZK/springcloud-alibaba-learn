package com.lucfer.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 这是一个自定义的自定义局部过滤器工厂类，要求两个：
 * 1.名字必须是 配置+GatewayFilterFactory
 * 2.必须继承AbstractGatewayFilterFactory<配置类>  泛型 用于接收配置类,配置类用于接收配置文件的配置
 */
@Component
@Slf4j
public class LogGatewayFilterFactory extends
        AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLog()) {
                    log.info("CacheLog日志已开启");
                }
                if (config.isConsoleLog()) {
                    log.info("ConsoleLog日志已开启");
                }
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    //如果不用static修饰 则出现异常：Failed to instantiate [com.lucfer.config.LogGatewayFilterFactory$Config]: No default constructor found;
    @Data
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;
    }
}
