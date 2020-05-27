package org.tubetrue01.usercenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tubetrue01.usercenter.mapper.BaseMapper;
import org.tubetrue01.usercenter.mapper.RoleInfoMapper;
import org.tubetrue01.usercenter.pojo.RoleInfo;
import org.tubetrue01.usercenter.service.RoleInfoService;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 8:29 下午
 * Description :
 */
@Service
public class RoleInfoServiceImpl extends AbstractBaseServiceImpl<Integer, RoleInfo> implements RoleInfoService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public BaseMapper<Integer, RoleInfo> mapper() {
        return this.roleInfoMapper;
    }

}
