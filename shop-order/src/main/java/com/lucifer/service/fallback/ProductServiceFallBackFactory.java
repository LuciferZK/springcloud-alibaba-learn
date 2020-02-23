package com.lucifer.service.fallback;


import com.lucifer.pojo.Product;
import com.lucifer.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//这是容器类，他要求我们要是实现一个FallbackFactory<T> T:为哪个接口产生容错类
/*
@Slf4j
@Service
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {

    //Throwable 这就是调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findById(Long pid) {
                log.error("{}",throwable);
                Product product = new Product();
                //此处设置一个不存在的pid
                product.setPid(-10000L);
                product.setPName("远程调用商品微服务出现了异常，进入容错逻辑");
                return product;
            }
        };
    }
}
*/
