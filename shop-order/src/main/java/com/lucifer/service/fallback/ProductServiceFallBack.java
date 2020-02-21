package com.lucifer.service.fallback;

import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import org.springframework.stereotype.Service;

//这是一个容错类，需要实现feign所在的接口，并去实现接口中的所有方法
//一旦feign远程调用出现问题了，就会进入当前类中同名方法，执行容错逻辑
@Service
public class ProductServiceFallBack implements ProductService {

    @Override
    public Product findById(Long pid) {
        Product product = new Product();
        //此处设置一个不存在的pid
        product.setPid(-10000L);
        product.setPName("远程调用商品微服务出现了异常，进入容错逻辑");
        return product;
    }
}
