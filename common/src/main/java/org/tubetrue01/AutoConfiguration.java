package org.tubetrue01;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.tubetrue01.configuration.SecurityConfigProperties;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 6:04 下午
 * Description :
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(SecurityConfigProperties.class)
public class AutoConfiguration {
}
