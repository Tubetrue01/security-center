package org.tubetrue01.usercenter.configuration.auth.handler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.tubetrue01.utils.Config;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("-==登陆成功！==-");
        var principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            var token = String.valueOf(Utils.IdUtils.generateId());
            var userInfo = (UserDetails) principal;
            var authorities = userInfo.getAuthorities();
            var authorizesList = new ArrayList<String>();
            var userInfoMap = new HashMap<String, Object>();
            authorities.forEach(
                    auth -> authorizesList.add(auth.getAuthority())
            );
            userInfoMap.put("username", userInfo.getUsername());
            userInfoMap.put("authorizesList", authorizesList);

            var jwt = Jwts.builder()
                    .setSubject(authentication.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 + Config.Security.JWT_EXPIRED_TIME))
                    .signWith(SignatureAlgorithm.HS256, Config.Security.JWT_SIGNING_KEY)
                    .compact();

            userInfoMap.put(Config.Security.JWT_PARAM_IN_HEADER, jwt);
            // The token info has the 60s lifetime
            Utils.RedisUtils.set(token, userInfoMap, Config.Security.TOKEN_EXPIRED_TIME);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(Utils.JSONUtils.objectToJson(
                    ResultRtn.of(StatusCode.LOGIN_SUCCESS, Map.of(Config.Security.TOKEN_PARAM_IN_header, token, Config.Security.JWT_PARAM_IN_HEADER, jwt))
            ));
        }
    }
}
