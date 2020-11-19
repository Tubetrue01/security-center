package com.gaolv.user.auth.service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 描述：认证客户端，需要覆盖掉父类的相关认证方法
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
public class AuthCenterProvider extends DaoAuthenticationProvider {

    @Autowired
    private AuthCenterServiceImpl authCenterService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken authentication) {
        log.info("-==认证成功==-");
    }

    @Override
    protected void doAfterPropertiesSet() {
        this.hideUserNotFoundExceptions = false;
        this.setUserDetailsService(authCenterService);
        super.doAfterPropertiesSet();
    }
}
