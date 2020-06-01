package org.tubetrue01.usercenter.configuration.auth.sms;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tubetrue01.utils.ResultRtn;
import org.tubetrue01.utils.StatusCode;
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
public class SmsCodeCheckFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (antPathMatcher.match("/user/login/mobile", httpServletRequest.getRequestURI())
                && ("post".equalsIgnoreCase(httpServletRequest.getMethod()))) {
            var isValid = validateSmsCode(new ServletWebRequest(httpServletRequest));
            if (!isValid) {
                log.error("-==短信码校验失败==-");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.getWriter().println(Utils.JSONUtils.objectToJson(ResultRtn.of(StatusCode.SMS_CODE_FAILURE)));
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean validateSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        // Get the sms code from request
        var smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        // Get the mobile from the request
        var mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
        log.info("-====SmsCode from request :{} ", smsCodeInRequest);
        if (smsCodeInRequest != null && !"".equals(smsCodeInRequest)) {
            var smsCodeFromRedis = Utils.RedisUtils.get(mobile);
            if (smsCodeFromRedis != null && smsCodeFromRedis.toString().equalsIgnoreCase(smsCodeInRequest)) {
                // Valid and delete it
                Utils.RedisUtils.delete(mobile);
                return true;
            }
        }
        return false;
    }
}
