package org.tubetrue01.usercenter.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.tubetrue01.usercenter.configuration.auth.handler.LoginFailureHandler;
import org.tubetrue01.usercenter.configuration.auth.handler.LoginSuccessHandler;
import org.tubetrue01.usercenter.configuration.auth.login.LoginForJsonAuthenticationFilter;
import org.tubetrue01.usercenter.configuration.auth.sms.SmsAuthenticationConfig;
import org.tubetrue01.usercenter.configuration.auth.sms.SmsCodeCheckFilter;
import org.tubetrue01.usercenter.service.impl.UserInfoServiceImpl;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 12:14 上午
 * Description :
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginSuccessHandler successHandler;
    @Autowired
    private LoginFailureHandler failureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Qualifier("userInfoService")
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Autowired
    private SmsCodeCheckFilter smsCodeCheckFilter;
    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(smsCodeCheckFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers("/sms/code").permitAll()
                .anyRequest().access("@RBACService.hasPermission(request, authentication)")
            .and()
                .formLogin()
            .and()
                .sessionManagement()
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .csrf().disable()
            .apply(smsAuthenticationConfig);

        http.addFilterAt(loginForJsonFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public LoginForJsonAuthenticationFilter loginForJsonFilter() throws Exception {
        var loginForJsonAuthenticationFilter = new LoginForJsonAuthenticationFilter();
        loginForJsonAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        loginForJsonAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        loginForJsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginForJsonAuthenticationFilter.setFilterProcessesUrl("/user/login");
        return loginForJsonAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
