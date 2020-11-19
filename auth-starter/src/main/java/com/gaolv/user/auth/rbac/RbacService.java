package com.gaolv.user.auth.rbac;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：权限校验服务
 *
 * @author Pengfei.Zhang by 2020/6/8
 */
public interface RbacService {
    /**
     * 判断是否拥有权限，用于对系统自身的请求进行校验
     *
     * @param request 请求对象
     * @return true 如果觉有当前请求的权限，否则返回false
     */
    boolean hasPermission(HttpServletRequest request);
}
