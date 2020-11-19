package com.gaolv.user.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：自动注入类
 *
 * @author PengfeiZhang by 2020/6/8
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"com.gaolv.user.auth"})
public class AuthAutoConfiguration {

}
