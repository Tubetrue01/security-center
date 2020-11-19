package com.gaolv.user.auth.authenticator;

import com.gaolv.user.auth.base.AuthenticatedToken;
import com.gaolv.user.auth.base.AuthenticatedUser;

import java.util.Map;

/**
 * 描述：认证器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
public interface Authenticator {
    /**
     * 认证方法
     *
     * @param authenticatedToken 需要认证的 token
     * @return 认证之后的对象
     */
    AuthenticatedUser authenticate(AuthenticatedToken authenticatedToken);

    /**
     * 认证器是否支持当前请求
     *
     * @param authenticatedToken 待验证的 token
     * @return true 如果支持验证该请求
     */
    boolean support(AuthenticatedToken authenticatedToken);

    /**
     * 登录成功之后的处理
     *
     * @param authenticatedUser 认证之后的用户信息
     * @return 返回该登录下的数据
     */
    Map<String, Object> loginSuccess(AuthenticatedUser authenticatedUser);

}
