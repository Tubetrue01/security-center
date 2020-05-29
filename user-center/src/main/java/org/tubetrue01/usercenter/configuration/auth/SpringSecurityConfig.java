package org.tubetrue01.usercenter.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.tubetrue01.usercenter.configuration.auth.filter.LoginForJsonFilter;
import org.tubetrue01.usercenter.configuration.auth.handler.LoginFailureHandler;
import org.tubetrue01.usercenter.configuration.auth.handler.LoginSuccessHandler;
import org.tubetrue01.usercenter.configuration.auth.sms.SmsAuthenticationConfig;
import org.tubetrue01.usercenter.configuration.auth.sms.SmsAuthenticationFilter;
import org.tubetrue01.usercenter.configuration.auth.sms.SmsCodeFilter;
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
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Autowired
    private SmsCodeFilter smsCodeFilter;
    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(smsAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
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
    public LoginForJsonFilter loginForJsonFilter() throws Exception {
        var loginForJsonFilter = new LoginForJsonFilter();
        loginForJsonFilter.setAuthenticationSuccessHandler(successHandler);
        loginForJsonFilter.setAuthenticationFailureHandler(failureHandler);
        loginForJsonFilter.setAuthenticationManager(authenticationManagerBean());
        loginForJsonFilter.setFilterProcessesUrl("/user/login");
        return loginForJsonFilter;
    }

    @Bean
    public SmsAuthenticationFilter smsAuthenticationFilter() throws Exception {
        var smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        return smsAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
