package org.tubetrue01.usercenter.configuration.auth.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
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
 * Date : 2020/5/28
 * Time : 11:21 上午
 * Description :
 */
@Log4j2
@Component
public class LogoutSuccessCustomizeHandler implements LogoutSuccessHandler {
    private static final String TOKEN = "token";
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var token = request.getHeader(TOKEN);
        response.setContentType("application/json;charset=utf-8");
        try {
            var out = response.getWriter();
            if (token == null || token.isEmpty()) {
                out.println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.LOGOUT_ERROR)));
            } else {
                Utils.RedisUtils.delete(token);
                log.info("-==[{}] logout success==-", token);
                out.println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.LOGOUT_SUCCESS)));
            }
        } catch (IOException e) {
            log.error(e);
        }
    }
}
