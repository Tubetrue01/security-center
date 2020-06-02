package org.tubetrue01.clienta.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.tubetrue01.utils.Config;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/31
 * Time : 2:40 下午
 * Description :
 */
@Log4j2
@Component
public class AuthenticationCustomizeEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        var token = request.getHeader(Config.Security.TOKEN_PARAM_IN_header);
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();
        log.warn("-==用户:[{}]越级访问:[{}]--[{}]==-", token, requestUri, requestMethod);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.NO_PERMISSION)));
    }
}
