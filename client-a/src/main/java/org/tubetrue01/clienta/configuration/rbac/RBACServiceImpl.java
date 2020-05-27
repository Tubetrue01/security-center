package org.tubetrue01.clienta.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.tubetrue01.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@SuppressWarnings("all")
public class RBACServiceImpl implements RBACService {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        var token = request.getHeader("token");
        var permissions = Utils.RedisUtils.get(token);
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();

        if (permissions instanceof List) {
            var permissionList = (List<String>) permissions;
            for (var grantedAuthority : permissionList) {
                var url_method = grantedAuthority.split(":");
                var url = url_method[0];
                var method = url_method[1];
                if (antPathMatcher.match(url, requestUri) && requestMethod.equalsIgnoreCase(method)) {
                    return true;
                }
            }
            log.warn("-==用户:[{}]越级访问:[{}]--[{}]==-", token, requestUri, requestMethod);
        }
        return false;
    }
}
