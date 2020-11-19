package com.gaolv.user.auth.service.rbac.impl;

import com.gaolv.user.auth.service.rbac.RbacService;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述： Rbac 认证中心
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component("RBACService")
@SuppressWarnings("all")
public class RbacServiceImpl implements RbacService {

    @Override
    public boolean hasPermission(HttpServletRequest request) {
        return false;
    }

}
