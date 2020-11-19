package com.gaolv.user.auth.handler;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：认证失败处理器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
public class AuthCenterLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        AuthenticationException e) throws IOException {
        log.debug("[{}]-用户登录失败:[{}]", "PROJECT_NAME", e.getMessage());
    }
}
