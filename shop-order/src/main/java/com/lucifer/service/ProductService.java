package com.lucifer.service;

import com.lucifer.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
 */

@FeignClient(value = "service-product") //value是指调用nacos中注册的哪个微服务
public interface ProductService {

    //@FeignClient的value+ @GetMapping的value值，其实就是完成的请求地址  "http://service-product/product/" + pid
    @GetMapping("/product/{pid}") //指定请求的URI的部分
    public Product findById(@PathVariable("pid") Long pid);


}