package com.gaolv.user.auth.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;

/**
 * 描述：认证用户
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class AuthenticatedUser extends User {

    /**
     * 认证用户明细
     */
    private final Object details;

    public AuthenticatedUser(String username, String password, List<GrantedAuthority> list, Object details) {
        super(username, password, list);
        this.details = details;
    }

    public AuthenticatedUser(String username, Object details) {
        this(username, "", Collections.emptyList(), details);
    }

}
