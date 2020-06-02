package org.tubetrue01.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/6/1
 * Time : 7:36 下午
 * Description :
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityConfigProperties {
    private String jwtSigningKey = "MyJwtSigningKey";  // Signing key for jwt
    private String jwtParamInHeader = "jwt";           // Jwt param name in request header
    private String tokenParamInHeader = "token";       // token param name in request header
    private long tokenExpiredTime = 60L;               // 60s
    private long jwtExpiredTime = 60L;                 // 60s
}
