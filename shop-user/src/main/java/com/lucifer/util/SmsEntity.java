package com.lucifer.util;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SmsEntity {

    //接收短信的手机号码
    @NotNull(message = "接收短信的手机号码，不能为空")
    private String phoneNumbers;

    //短信签名名称。请在控制台签名管理页面签名名称一列查看。
    @NotNull(message = "短信签名名称，不能为空")
    private String signName;

    //短信模板ID。请在控制台模板管理页面模板CODE一列查看。
    @NotNull(message = "短信模板ID，不能为空")
    private String templateCode;

}
