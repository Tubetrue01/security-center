package org.zpf.usercenter.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 12:17 上午
 * Description :
 */
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public String info() {
        return "Success";
    }
}
