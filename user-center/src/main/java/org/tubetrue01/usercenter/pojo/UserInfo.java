package org.tubetrue01.usercenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:15 下午
 * Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements UserDetails {

    private Integer id;                             // 用户编号
    private String username;                        // 用户名
    private String password;                        // 密码
    private Integer roleId;                         // 角色编号
    private List<InterfaceInfo> interfaceInfoList;     // 权限列表

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getInterfaceInfoList();
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
