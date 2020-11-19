package com.gaolv.user.auth.entrypoint;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：权限认证统一拦截点
 *
 * @author PengfeiZhang by 2020/6/8
 */
@Log4j2
@Component
public class AuthenticationCustomizeEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();
        log.warn("[{}]-用户:[{}]越级访问:[{}]--[{}]==-", "", "", requestUri, requestMethod);
        response.getWriter().println();
    }
}
