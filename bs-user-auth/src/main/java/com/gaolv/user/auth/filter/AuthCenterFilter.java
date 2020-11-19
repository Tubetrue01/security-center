package com.gaolv.user.auth.filter;

import com.gaolv.user.auth.authenticator.Authenticator;
import com.gaolv.user.auth.base.AuthenticatedToken;
import com.gaolv.user.auth.base.RequestWrapper;
import com.gaolv.user.auth.context.AuthenticationContextHolder;
import com.gaolv.user.auth.enums.AuthType;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：集中认证过滤器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
@Component
public class AuthCenterFilter extends OncePerRequestFilter {

    private final List<Authenticator> authenticators;

    public AuthCenterFilter(List<Authenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        var url = request.getRequestURL().toString();
        // var token = request.getHeader(Constants.TOKEN_PARAM);
        // var clientId = request.getHeader(Constants.CLIENT_ID_PARAM);

        // 是否需要跳过登录验证
        var isSkip = false;

        /*
         * 是否放行包含以下几种类型：
         * 1、如果是 app 的请求，白名单，，那么 client-id、login-free 同时校验，避免攻击，如果检测符合，放行
         * 2、如果是 app 的请求，非白名单，那么 client-id、token 符合，放行
         * 3、如果是后台的请求，那么需要验证 token 是否有效，决定放行
         * 4、K8s 的监控节点，放行
         * 5、三方回调的接口直接放行
         * 6、内网调用的接口根据真实 ip 地址判断是否是内网决定放行
         */
        // if (isSkip(clientId, url) || isAppValid(clientId, token) || isTokenValid(token)) {
        // isSkip = true;
        // }

        if (isSkip) {
            request = prepare(request);
            chain.doFilter(request, response);
        } else {
            // log.warn("[{}]-url:[{}]-Token:[{}]登录超时", "PROJECT_NAME", url, token);
            // response.setContentType(CONTENT_TYPE);
            // response.getWriter().println(JsonUtils.objectToJson(ResultRtn.fail(UserStatusCode.LOGIN_TIMEOUT,
        }
    }

    /**
     * 准备方法，根据当前请求的校验级别对参数进行封装，供后续的校验使用
     *
     * @param request 请求对象
     * @throws IOException 获取请求体参数时可能抛出的异常
     * @return 返回 request对象，如果需要登陆，那么返回包装之后的 request 对象
     */
    private HttpServletRequest prepare(HttpServletRequest request) throws IOException {
        var authenticatedToken = new AuthenticatedToken();
        var authType = request.getHeader("auth-type");
        var clientType = request.getHeader("User-Agent");
        var clientId = request.getHeader("client-id");
        // 如果不是登录请求直接返回
        if (authType == null) {
            return request;
        }
        authenticatedToken.setAuthType(AuthType.valueOf(authType));
        authenticatedToken.setClientType(clientType);
        authenticatedToken.setClientId(clientId);
        // 保障请求
        var requestMapper = new RequestWrapper(request);
        for (var authenticator : authenticators) {
            if (authenticator.support(authenticatedToken)) {
                // authenticatedToken.setParamMap(RequestUtils.getJsonParam(requestMapper));
                AuthenticationContextHolder.set(authenticatedToken);
                break;
            }
        }
        return requestMapper;
    }
}
