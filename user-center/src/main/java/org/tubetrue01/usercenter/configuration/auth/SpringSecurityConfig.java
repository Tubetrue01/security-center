package org.tubetrue01.usercenter.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.tubetrue01.usercenter.configuration.auth.filter.LoginForJsonFilter;
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
             .and()
                .userDetailsService(userInfoService)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout()
                   .logoutUrl("/user/logout")
                   .logoutSuccessHandler(logoutSuccessHandler)
             .and()
                .csrf().disable();
        http.addFilterAt(loginForJsonFilter(), UsernamePasswordAuthenticationFilter.class);
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
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
