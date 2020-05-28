package org.tubetrue01.usercenter.configuration.auth.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 12:53 下午
 * Description :
 */
public interface RBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
