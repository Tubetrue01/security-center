package org.tubetrue01.usercenter.configuration.auth.sms;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/29
 * Time : 5:00 下午
 * Description :
 */
public class SmsCodeException extends AuthenticationServiceException {
    public SmsCodeException(String msg) {
        super(msg);
    }
}
