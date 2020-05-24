package org.zpf.usercenter.mapper;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zpf.usercenter.pojo.User;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 12:29 上午
 * Description :
 */
@Log4j2
@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByName(String username) {
        var password = passwordEncoder.encode("admin");
        log.info("-==admin加密之后：{}==-", password);
        if ("admin".equals(username)) {
            return new User("admin", password);
        }
        return null;
    }
}
