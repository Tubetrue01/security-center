package org.tubetrue01.clienta.configuration.rbac;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
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
@Component
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("token");
        if (token == null) {
            response.setHeader("content-type", "application/json;charset=UTF-8");
            response.getWriter().println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.LOGIN_TIMEOUT)));
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
