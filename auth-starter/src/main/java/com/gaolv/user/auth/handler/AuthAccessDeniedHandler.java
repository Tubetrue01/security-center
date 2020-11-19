package com.gaolv.user.auth.handler;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：拦截权限不足的请求
 *
 * @author PengfeiZhang by 2020/6/14
 */
@Log4j2
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();
        log.warn("[{}]-用户:[{}]禁止访问:[{}]--[{}]==-", "PROJECT_NAME", "token", requestUri, requestMethod);
        response.getWriter().println("JsonUtils.objectToJson(ResultRtn.of(UserStatusCode.LOGIN_TIMEOUT))");
    }
}
