package org.tubetrue01.usercenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 10:46 上午
 * Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterfaceInfo implements GrantedAuthority {
    private Integer id;                 // 编号
    private String interfaceUrl;        // 接口URL
    private String method;              // 接口方法

    @Override
    public String getAuthority() {
        return this.getInterfaceUrl();
    }
}
