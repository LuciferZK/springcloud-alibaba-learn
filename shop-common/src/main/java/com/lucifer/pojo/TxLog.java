package com.lucifer.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//消息事务状态记录
@TableName(value = "shop-txLog")
@Data
public class TxLog {

    @TableId
    private String txId;

    private Date date;
}
