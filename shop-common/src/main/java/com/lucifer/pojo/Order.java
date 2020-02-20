package com.lucifer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 订单
 */
@Data
@TableName(value = "shop_order")
public class Order {

    //主键,订单id
    @TableId(type = IdType.ASSIGN_ID)
    private Long oid;

    //用户id
    @TableField(value = "uid")
    private Long uid;

    //用户名
    @TableField(value = "username")
    private String username;

    //商品id
    @TableField(value = "pid")
    private Long pid;

    //商品名称
    @TableField(value = "name")
    private String pName;

    //商品单价
    @TableField(value = "price")
    private Double price;

    //购买数量
    @TableField(value = "number")
    private Long number;

}
