package org.tubetrue01.usercenter.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tubetrue01.usercenter.pojo.UserInfo;
import org.tubetrue01.usercenter.service.UserInfoService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:12 下午
 * Description :
 */
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Qualifier("userInfoService")
    @Autowired
    private UserInfoService userServiceInfo;

    @GetMapping("/all")
    public List<UserInfo> userInfo() {
        log.info("-==获取所有用户信息==-");
        return this.userServiceInfo.findAll();
    }
}
