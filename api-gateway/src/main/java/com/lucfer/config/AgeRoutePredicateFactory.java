package com.lucfer.config;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 这是一个自定义的路由断言工厂类，要求两个：
 * 1.名字必须是 配置+RoutePredicateFactory
 * 2.必须继承AbstractRoutePredicateFactory<配置类>  泛型 用于接收配置类,配置类用于接收配置文件的配置
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //断言
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //从serverWebExchange获取传入的参数
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isNotEmpty(ageStr)) {
                    int age = Integer.parseInt(ageStr);
                    return age < config.getMaxAge() && age > config.getMinAge();
                }
                return true;
            }
        };
    }

    //用于配置文件中获取参数值复制到配置类中的属性上
    @Override
    public List<String> shortcutFieldOrder() {
        //这里的顺序要跟配置文件中的参数顺序保持一致
        return Arrays.asList("minAge", "maxAge");
    }

    @Data
    public static class Config {
        Integer minAge;
        Integer maxAge;
    }
}