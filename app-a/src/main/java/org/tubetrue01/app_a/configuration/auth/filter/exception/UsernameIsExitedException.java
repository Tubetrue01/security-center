package org.tubetrue01.app_a.configuration.auth.filter.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/28
 * Time : 5:52 下午
 * Description :
 */
public class UsernameIsExitedException  extends AuthenticationException {
    public UsernameIsExitedException(String msg) {
        super(msg);
    }
}
