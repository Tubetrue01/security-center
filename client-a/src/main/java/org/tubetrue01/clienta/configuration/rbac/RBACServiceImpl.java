package org.tubetrue01.clienta.configuration.rbac;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.tubetrue01.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    private static final long TOKEN_EXPIRE = 60L;  // 60s

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        var token = request.getHeader("token");
        var userInfoMapFromRedis = Utils.RedisUtils.get(token);
        var requestUri = request.getRequestURI();
        var requestMethod = request.getMethod();

        if (userInfoMapFromRedis instanceof Map) {
            var userInfoMap = (Map<String, Object>) userInfoMapFromRedis;
            var permissionList = (List<String>) userInfoMap.get("authorizesList");
            for (var grantedAuthority : permissionList) {
                var url_method = grantedAuthority.split(":");
                var url = url_method[0];
                var method = url_method[1];
                if (antPathMatcher.match(url, requestUri) && requestMethod.equalsIgnoreCase(method)) {
                    // I think i need to udpate the token expired time if i can access this url
                    log.info("-==成功访问URL，更新Token令牌的过期时间==-");
                    Utils.RedisUtils.updateExpired(token,TOKEN_EXPIRE);
                    return true;
                }
            }
        }
        return false;
    }
}
