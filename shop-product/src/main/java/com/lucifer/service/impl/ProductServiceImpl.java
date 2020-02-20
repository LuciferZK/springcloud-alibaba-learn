package com.lucifer.service.impl;

import com.lucifer.dao.ProductDao;
import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public Product findByPid(Long pid) {
        return productDao.selectById(pid);
    }
}
