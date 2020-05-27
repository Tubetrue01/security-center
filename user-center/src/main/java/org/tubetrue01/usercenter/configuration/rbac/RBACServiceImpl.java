package org.tubetrue01.usercenter.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 12:55 下午
 * Description :
 */
@Log4j2
@Component("RBACService")
public class RBACServiceImpl implements RBACService {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        var principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            var user = ((UserDetails) principal);
            var authorities = user.getAuthorities();
            var username = user.getUsername();
            var requestUrl = request.getRequestURI();

            for (GrantedAuthority url : authorities) {
                if (antPathMatcher.match(url.getAuthority(), requestUrl)) {
                    return true;
                }
            }
            log.warn("-==用户:[{}]越级访问:[{}]==-", username, requestUrl);
        }
        return false;
    }
}
