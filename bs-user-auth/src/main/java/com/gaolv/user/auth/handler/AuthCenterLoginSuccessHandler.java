package com.gaolv.user.auth.handler;

import com.gaolv.user.auth.authenticator.Authenticator;
import com.gaolv.user.auth.base.AuthenticatedUser;
import com.gaolv.user.auth.context.AuthenticationContextHolder;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：登录成功处理器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
public class AuthCenterLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private List<Authenticator> authenticatorList;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        var principal = authentication.getPrincipal();

        if (principal instanceof AuthenticatedUser) {
            // 转换成认证用户对象
            var userInfo = (AuthenticatedUser)principal;

            // 最终结果集
            Map<String, Object> resultMap = null;

            for (var authenticator : authenticatorList) {
                if (authenticator.support(AuthenticationContextHolder.get())) {
                    // 清空认证 token
                    AuthenticationContextHolder.clear();
                    resultMap = authenticator.loginSuccess(userInfo);
                    break;
                }
            }

            // response.setContentType(CONTENT_TYPE);
            // response.getWriter().println(JsonUtils
            // .objectToJson(ResultRtn.of(GenericStatusCode.SUCCESS, resultMap)));
            log.info("[{}]-用户-[{}]-登录成功", "PROJECT_NAME", userInfo.getUsername());
        }
    }
}
