package org.tubetrue01.usercenter.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tubetrue01.usercenter.pojo.RoleInfo;
import org.tubetrue01.usercenter.service.RoleInfoService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 8:25 下午
 * Description :
 */
@Log4j2
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleInfoService roleInfoService;

    @GetMapping("/all")
    public List<RoleInfo> roleInfos() {
        log.info("-==获取角色信息成功！==-");
        return this.roleInfoService.findAll();
    }
}
