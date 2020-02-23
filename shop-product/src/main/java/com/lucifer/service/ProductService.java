package com.lucifer.service;

import com.lucifer.pojo.Product;

public interface ProductService {
    //根据pid查询商品信息
    Product findByPid(Long pid);

    //扣减库存
    void reduceInventory(Long pid, Integer number);
}
