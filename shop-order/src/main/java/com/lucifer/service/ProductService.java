package com.lucifer.service;

import com.lucifer.pojo.Product;
/*import com.lucifer.service.fallback.ProductServiceFallBack;
import com.lucifer.service.fallback.ProductServiceFallBackFactory;*/
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
 */
//value是指调用nacos中注册的哪个微服务
//fallback指定当前feign接口的容错类
@FeignClient(
        value = "service-product"//,
        // fallback = ProductServiceFallBack.class,
        //fallbackFactory = ProductServiceFallBackFactory.class
        //fallback 与 fallbackFactory 两者不能同时存在
)
public interface ProductService {

    //@FeignClient的value+ @GetMapping的value值，其实就是完成的请求地址  "http://service-product/product/" + pid
    @GetMapping("/product/{pid}") //指定请求的URI的部分
    public Product findById(@PathVariable("pid") Long pid);

    //扣减库存
    //参数1：商品标识
    //参数2：扣减数量
    @RequestMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Long pid,@RequestParam("number") Integer number);
}
