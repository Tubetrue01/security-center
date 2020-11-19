package com.gaolv.user.auth.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * 描述：认证异常基类
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
public class AuthCenterException extends InternalAuthenticationServiceException {
    public AuthCenterException(String msg) {
        super(msg);
    }
}
