package com.lucifer.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户
 */
@Data
@TableName(value = "shop_user")
public class User {

    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;

    //用户名
    @TableField(value = "user_name")
    private String username;

    //密码
    @TableField(value = "password")
    private String password;

    //手机号
    @TableField(value = "telephone")
    private String telephone;

}
