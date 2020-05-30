package org.tubetrue01.usercenter.configuration.auth.sms;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tubetrue01.utils.Utils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/29
 * Time : 4:54 下午
 * Description :
 */
@Log4j2
@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (antPathMatcher.match("/user/login/mobile", httpServletRequest.getRequestURI())
                && ("post".equalsIgnoreCase(httpServletRequest.getMethod()))) {
            try {
                validateSmsCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidateSmsCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        var smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        var mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
        var smsCodeFromRedis = Utils.RedisUtils.get(mobile);

        log.info("-====SmsCode From request :{} ", smsCodeInRequest);

        if (smsCodeInRequest == null || smsCodeInRequest.equals("")) {
            throw new ValidateSmsCodeException("验证码不能为空！");
        } else if (smsCodeFromRedis == null) {
            throw new ValidateSmsCodeException("验证码不存在，请重新发送！");
        } else if (!smsCodeFromRedis.toString().equalsIgnoreCase(smsCodeInRequest)) {
            throw new ValidateSmsCodeException("验证码不正确！");
        }
        // Valid success and delete it
        Utils.RedisUtils.delete(mobile);
    }
}
