package org.tubetrue01.usercenter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tubetrue01.usercenter.handler.FailureHandler;
import org.tubetrue01.usercenter.handler.SuccessHandler;
import org.tubetrue01.usercenter.service.UserInfoServiceImpl;

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
    private UserInfoServiceImpl userInfoService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .permitAll()
                .successHandler(successHandler).failureHandler(failureHandler).and()
                .userDetailsService(userInfoService)
                .csrf().disable();
    }
}
