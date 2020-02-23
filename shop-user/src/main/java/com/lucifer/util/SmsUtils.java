package com.lucifer.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

//发送短信的工具类
@Slf4j
public class SmsUtils {

    private static final String regionId = "cn-hangzhou";

    private static final String accessKeyId = "xxxxx";

    private static final String secret = "xxxxx";

    private static final String doMain = "dysmsapi.aliyuncs.com";

    private static final String action = "SendSms";

    public static CommonResponse SendSms(SmsEntity smsEntity) {

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(doMain);
        request.setVersion("2017-05-25");
        request.setAction(action);
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", smsEntity.getPhoneNumbers());
        request.putQueryParameter("SignName", smsEntity.getSignName());
        request.putQueryParameter("TemplateCode", smsEntity.getTemplateCode());
        //随机生成1-999999之间的随机数
        String randomNumeric = RandomStringUtils.randomNumeric(6);
        SmsParam smsParam = new SmsParam(randomNumeric);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(smsParam));
        CommonResponse response = new CommonResponse();
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            log.error("短信发送失败，{}",response);
            e.printStackTrace();
        }
        return response;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class SmsParam {
    private String code;
}
