package org.tubetrue01.clienta.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tubetrue01.utils.Config;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 8:49 下午
 * Description :
 */
@Log4j2
@Component
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader(Config.Security.TOKEN_PARAM_IN_header);
        if (token == null || Utils.RedisUtils.get(token) == null) {
            log.warn("Token:[{}]登陆超时", token);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.LOGIN_TIMEOUT)));
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
