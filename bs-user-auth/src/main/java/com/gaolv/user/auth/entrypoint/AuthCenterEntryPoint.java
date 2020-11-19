package com.gaolv.user.auth.entrypoint;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：用于后台权限拦截
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
@SuppressWarnings("unused")
public class AuthCenterEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        // var token = request.getHeader(TOKEN_PARAM);
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();
        // log.warn("[{}]-用户:[{}]越级访问:[{}]--[{}]==-", PROJECT_NAME, token, requestUri, requestMethod);
        // response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // response.getWriter().println(JsonUtils.objectToJson(ResultRtn.of(UserStatusCode.NO_PERMISSION)));
    }
}
