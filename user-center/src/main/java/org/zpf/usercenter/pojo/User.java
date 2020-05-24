package org.zpf.usercenter.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 12:21 上午
 * Description :
 */
public class User implements UserDetails {

    private String username;
    private String password;
    private boolean isExpired = false;
    private boolean isLock = false;
    private boolean isEnable = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}
