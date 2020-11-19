package com.gaolv.user.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：客户端的类型
 *
 * @author chenpan by 2020/10/14
 */
@Getter
@AllArgsConstructor
public enum ClientType {
    /**
     * Android端
     */
    ANDROID("Android"),
    /**
     * iOS端
     */
    IOS("IOS");

    private final String type;
}
