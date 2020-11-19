package com.gaolv.user.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：认证方式
 * 目前web和小程序的不统计 暂时先标记为0
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Getter
@AllArgsConstructor
public enum AuthType {
    /**
     * web 后台用户名密码登录
     */
    WEB_USERNAME_PASSWORD((byte)0),
    /**
     * web 短信码登录
     */
    WEB_SMS_CODE((byte)0),
    /**
     * app 短信码登录
     */
    APP_SMS_CODE((byte)1),
    /**
     * app 一键登录
     */
    APP_QUICK_LOGIN((byte)2),
    /**
     * 微信小程序用户名密码登录
     */
    WECHAT_USERNAME_PASSWORD((byte)0);

    /**
     * 登录方式对应的code
     */
    private final byte code;

}
