package com.gaolv.user.auth.service;

import com.gaolv.user.auth.authenticator.Authenticator;
import com.gaolv.user.auth.base.AuthenticatedToken;
import com.gaolv.user.auth.base.AuthenticatedUser;
import com.gaolv.user.auth.context.AuthenticationContextHolder;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 描述：认证中心服务
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
public class AuthCenterServiceImpl implements UserDetailsService {
    @Autowired
    private List<Authenticator> authenticators;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取验证 token
        var authenticatedToken =
            Optional.ofNullable(AuthenticationContextHolder.get()).orElseGet(AuthenticatedToken::new);
        // 获取验证后的信息
        var authenticatedUser = authenticate(authenticatedToken);

        return authenticatedUser.orElseThrow(() -> new UsernameNotFoundException("用户名不存在"));
    }

    /**
     * 认证用户
     *
     * @param authenticatedToken 需要认证的 token
     * @return 返回认证的用户信息
     */
    private Optional<AuthenticatedUser> authenticate(AuthenticatedToken authenticatedToken) {
        for (var authenticator : authenticators) {
            if (authenticator.support(authenticatedToken)) {
                return Optional.ofNullable(authenticator.authenticate(authenticatedToken));
            }
        }
        return Optional.empty();
    }

}
