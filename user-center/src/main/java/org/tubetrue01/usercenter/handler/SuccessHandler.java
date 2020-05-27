package org.tubetrue01.usercenter.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 12:41 上午
 * Description :
 */
@Log4j2
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-==登陆成功！==-");
        var principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            var token = Utils.IdUtils.generateId();
            var userInfo = (UserDetails) principal;
            var authorities = userInfo.getAuthorities();
            Utils.RedisUtils.set(String.valueOf(token), authorities);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/json;charset=UTF-8");
            response.getWriter().println(Utils.JSONUtils.objectToJson(
                    ResultRtn.of(StatusCode.LOGIN_SUCCESS, Map.of("token", token))
            ));
        }
    }
}
