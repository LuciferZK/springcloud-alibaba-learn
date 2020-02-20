package com.lucifer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品
 */
@Data
@TableName(value = "shop_product")
public class Product {

    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long pid;

    //商品名称
    @TableField(value = "name")
    private String pName;

    //商品价格
    @TableField(value = "price")
    private Double price;

    //库存
    @TableField(value = "stock")
    private Long stock;


}
