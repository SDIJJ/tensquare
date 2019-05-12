package com.tensquare.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/11 18:30
 * @version: v1.0.0
 */
@RabbitListener(queues = "sms")
@Component
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;
    @RabbitHandler
    public void sendMsg(Map<String, String> map) throws ClientException {
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        System.out.println("手机号： " + mobile);
        System.out.println("验证码： " + checkcode);
        SendSmsResponse sendSmsResponse = smsUtil.sendSms(mobile, checkcode);
    }
}
