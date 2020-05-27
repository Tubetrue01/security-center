package org.tubetrue01.clienta.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 6:07 下午
 * Description :
 */
@Log4j2
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/all")
    public String userInfo() {
        log.info("-==Enter the role info by the client-a==-");
        return "/role/all from client-a";
    }
}
