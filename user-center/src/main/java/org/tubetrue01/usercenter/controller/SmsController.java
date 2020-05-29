package org.tubetrue01.usercenter.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tubetrue01.usercenter.pojo.SmsCode;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/29
 * Time : 3:26 下午
 * Description :
 */
@Log4j2
@RestController
@RequestMapping("/sms")
public class SmsController {

    @PostMapping("/code")
    public ResultRtn<Map<String, Object>> createSmsCode(@RequestBody Map<String, String> paramMap) {
        // Sed the msg to msg server
        var mobile = paramMap.get("mobile");
        SmsCode smsCode = createSMSCode();
        log.info("您的手机[{}]登录验证码为: [{}] 有效时间为60秒", mobile, smsCode.getCode());
        // Put the code to redis and bind it with phone number
        var expireSecond = (long) smsCode.getExpireTime().getSecond();
        Utils.RedisUtils.set(mobile, smsCode.getCode(), expireSecond);
        return ResultRtn.of(StatusCode.SUCCESS);
    }

    private SmsCode createSMSCode() {
        var code = String.valueOf(Utils.IdUtils.generateId());
        return new SmsCode(code, 60);
    }
}
