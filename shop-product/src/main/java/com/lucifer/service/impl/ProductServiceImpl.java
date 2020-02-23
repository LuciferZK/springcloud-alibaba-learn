package com.lucifer.service.impl;

import com.lucifer.dao.ProductDao;
import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public Product findByPid(Long pid) {
        return productDao.selectById(pid);
    }

    @Transactional
    @Override
    public void reduceInventory(Long pid, Integer number) {
        //查询
        Product product = productDao.selectById(pid);
        //省略
        //内存中扣减
        product.setStock(product.getStock() - number);

        //模拟异常
        int a=1/0;

        //保存
        productDao.updateById(product);
    }
}
