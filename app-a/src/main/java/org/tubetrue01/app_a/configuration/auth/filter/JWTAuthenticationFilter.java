package org.tubetrue01.app_a.configuration.auth.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.tubetrue01.app_a.configuration.auth.filter.exception.UsernameIsExitedException;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
import org.tubetrue01.utils.Utils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/28
 * Time : 5:49 下午
 * Description :
 */
@Log4j2
@Component
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var token = request.getHeader("jwt");
        log.info("-==Get the token is : [{}]==-", token);
        if (token == null || token.isEmpty()) {
            log.info("-==Token is null and system return==-");
            response.setHeader("content-type", "application/json;charset=utf-8");
            response.getWriter().println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.JWT_IS_NULL)));
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        var claims = Jwts.parser().setSigningKey("MyJwtSecret")
                .parseClaimsJws(token)
                .getBody();

        var username = claims.getSubject();
        var expiration = claims.getExpiration();
        var now = new Date();

        if (now.getTime() > expiration.getTime()) {
            throw new UsernameIsExitedException("该账号已过期,请重新登陆");
        }

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }

}
