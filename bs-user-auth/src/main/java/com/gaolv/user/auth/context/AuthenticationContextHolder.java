package com.gaolv.user.auth.context;

import com.gaolv.user.auth.base.AuthenticatedToken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 描述：安全认证上下文
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticationContextHolder {
    /**
     * 保留上下文 token
     */
    private static final ThreadLocal<AuthenticatedToken> TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取当前上下文 token
     *
     * @return 返回当前请求的 token 对象
     */
    public static AuthenticatedToken get() {
        return TOKEN_THREAD_LOCAL.get();
    }

    /**
     * 为当前请求设置 token
     *
     * @param authenticatedToken 当前请求的 token 对象
     */
    public static void set(AuthenticatedToken authenticatedToken) {
        TOKEN_THREAD_LOCAL.set(authenticatedToken);
    }

    /**
     * 清空
     */
    public static void clear() {
        TOKEN_THREAD_LOCAL.remove();
    }
}
