package com.gaolv.user.auth;

import com.gaolv.user.auth.authenticator.Authenticator;
import com.gaolv.user.auth.entrypoint.AuthCenterEntryPoint;
import com.gaolv.user.auth.filter.AuthCenterFilter;
import com.gaolv.user.auth.service.AuthCenterServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;

/**
 * 描述：安全认证配置
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthCenterServiceImpl authCenterService;
    @Autowired
    private AuthCenterEntryPoint authCenterEntryPoint;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private List<Authenticator> authenticators;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest()
            .access("@RBACService.hasPermission(request)").and()
            .exceptionHandling()
            .authenticationEntryPoint(authCenterEntryPoint).and()
            .formLogin()
            .loginProcessingUrl("/login")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandler).and()
            .headers()
            .frameOptions()
            .sameOrigin().and()
            .csrf().disable();
        http.addFilterBefore(new AuthCenterFilter(authenticators), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
