package com.lucifer.controller;

import com.alibaba.fastjson.JSON;
import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ProductController {

    @Resource
    ProductService productService;

    @GetMapping("product/api1/demo1")
    public String demo1() {
        return "demo";
    }

    @GetMapping("product/api1/demo2")
    public String demo2() {
        return "demo";
    }

    @GetMapping("product/api2/demo1")
    public String demo3() {
        return "demo";
    }

    @GetMapping("product/api2/demo2")
    public String demo4() {
        return "demo";
    }



    //商品信息查询
    @GetMapping("product/{pid}")
    public Product findProduct(@PathVariable("pid") Long pid) {
        log.info("商品查询pid:{}", pid);
        Product product = productService.findByPid(pid);
        log.info("商品查询结果:{}", JSON.toJSONString(product));
        return product;
    }

    //扣减库存
    @RequestMapping("/product/reduceInventory")
    public void reduceInventory(Long pid,Integer number) {
        productService.reduceInventory(pid,number);
    }

}
