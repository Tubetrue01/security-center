package com.gaolv.user.auth.base;

import com.gaolv.user.auth.enums.AuthType;

import lombok.Data;

import java.util.Map;

/**
 * 描述：待认证的 token
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Data
public class AuthenticatedToken {
    /**
     * 需要验证的用户名或者手机号
     */
    private String username;
    /**
     * 请求的 url
     */
    private AuthType authType;
    /**
     * 请求参数封装
     */
    private Map<String, Object> paramMap;

}
