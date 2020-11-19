package com.gaolv.user.auth.filter;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：登录拦截器
 *
 * @author PengfeiZhang by 2020/6/8
 */
@Log4j2
public class LoginFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        var url = request.getRequestURL().toString();
        var token = request.getHeader("TOKEN_PARAM");
        var clientId = request.getHeader("CLIENT_ID_PARAM");

        // 是否需要跳过登录验证
        var isSkip = false;

        // 自定义白名单
        // if (isSkip(clientId, url) ){
        // isSkip = true;
        // }

        if (isSkip) {
            chain.doFilter(request, response);
        } else {
            log.warn("[{}]-url:[{}]-Token:[{}]登录超时", "PROJECT_NAME", url, token);
            var tips = String.format("请检查该token：[%s]是否失效", token);
            response.getWriter().println(tips);
        }
    }
}
