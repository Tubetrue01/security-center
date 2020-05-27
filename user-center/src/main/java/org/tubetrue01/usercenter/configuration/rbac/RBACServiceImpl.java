package org.tubetrue01.usercenter.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
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
            var requestMethod = request.getMethod();

            for (var grantedAuthority : authorities) {
                var interfaceInfo = grantedAuthority.getAuthority();
                var url_method = interfaceInfo.split(":");
                var url = url_method[0];
                var method = url_method[1];
                if (antPathMatcher.match(url, requestUrl) && requestMethod.equalsIgnoreCase(method)) {
                    return true;
                }
            }
            log.warn("-==用户:[{}]越级访问:[{}]--[{}]==-", username, requestUrl, requestMethod);
        }
        return false;
    }
}
