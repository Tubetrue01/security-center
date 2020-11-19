package com.gaolv.user.auth.rbac;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：权限校验实现
 *
 * @author Pengfei.Zhang by 2020/6/8
 */
@Log4j2
@Component("RBACService")
public class RbacServiceImpl implements RbacService {

    @Override
    public boolean hasPermission(HttpServletRequest request) {
        return false;
    }
}
