package org.tubetrue01.usercenter.configuration.auth.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/28
 * Time : 1:55 下午
 * Description :
 */
@Log4j2
public class LoginForJsonFilter extends UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        var mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest = null;
        try (var is = request.getInputStream()) {
            var authenticationBean = mapper.readValue(is, Map.class);
            var username = authenticationBean.get("username");
            var password = authenticationBean.get("password");
            authRequest = new UsernamePasswordAuthenticationToken(username, password);

        } catch (IOException e) {
            log.error(e);
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        } finally {
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}
