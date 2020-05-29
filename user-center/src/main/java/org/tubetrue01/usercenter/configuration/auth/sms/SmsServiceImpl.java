package org.tubetrue01.usercenter.configuration.auth.sms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tubetrue01.usercenter.pojo.UserInfo;
import org.tubetrue01.usercenter.service.impl.UserInfoServiceImpl;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/29
 * Time : 5:27 下午
 * Description :
 */
@Component
@Qualifier("smsService")
public class SmsServiceImpl extends UserInfoServiceImpl {

    @Override
    protected UserInfo createUserInfo(String mobile) {
        var userInfo = new UserInfo();
        userInfo.setMobile(mobile);
        return userInfo;
    }
}
