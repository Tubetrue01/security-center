package com.gaolv.user.auth.handler;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：后台注销成功处理器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
@SuppressWarnings("unused")
public class AuthCenterLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) {
        // var token = request.getHeader(TOKEN_PARAM);
        // // response.setContentType(CONTENT_TYPE);
        // try {
        // var out = response.getWriter();
        // if (token == null || token.isEmpty()) {
        // // out.println(JsonUtils.objectToJson(ResultRtn.of(UserStatusCode.LOGOUT_ERROR)));
        // } else {
        // // var userId = Utils.TokenUtils.parse(token).get(USER_ID);
        // // redis.getRedisTemplate().delete(userId);
        // log.info("[{}]-[{}] 注销成功", "PROJECT_NAME", token);
        // // out.println(JsonUtils.objectToJson(ResultRtn.of(GenericStatusCode.SUCCESS)));
        // }
        // } catch (IOException e) {
        // log.error("[{}]-[{}] 注销失败", "PROJECT_NAME", token, e);
        // }
    }
}
