package org.tubetrue01.usercenter.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.tubetrue01.usercenter.handler.FailureHandler;
import org.tubetrue01.usercenter.handler.SuccessHandler;
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
    private SuccessHandler successHandler;
    @Autowired
    private FailureHandler failureHandler;
    @Autowired
    private LogoutHandler logoutHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
                     .anyRequest().access("@RBACService.hasPermission(request, authentication)")
             .and()
                .formLogin()
                   .loginPage("/login.html")
                   .loginProcessingUrl("/user/login").permitAll()
                   .successHandler(successHandler)
                   .failureHandler(failureHandler)
             .and()
                .userDetailsService(userInfoService)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout()
                   .addLogoutHandler(logoutHandler)
                   .logoutUrl("/user/logout")
                   .logoutSuccessHandler(logoutSuccessHandler)
             .and()
                .csrf().disable();
    }
}
