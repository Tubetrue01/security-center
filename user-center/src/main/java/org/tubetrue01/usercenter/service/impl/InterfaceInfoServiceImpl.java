package org.tubetrue01.usercenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tubetrue01.usercenter.mapper.BaseMapper;
import org.tubetrue01.usercenter.mapper.InterfaceInfoMapper;
import org.tubetrue01.usercenter.mapper.RoleInfoInterfaceInfoMapper;
import org.tubetrue01.usercenter.pojo.InterfaceInfo;
import org.tubetrue01.usercenter.service.InterfaceInfoService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 10:55 上午
 * Description :
 */
@Service
public class InterfaceInfoServiceImpl extends AbstractBaseServiceImpl<Integer, InterfaceInfo> implements InterfaceInfoService {
    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;
    @Autowired
    private RoleInfoInterfaceInfoMapper roleInfoInterfaceInfoMapper;

    @Override
    public BaseMapper<Integer, InterfaceInfo> mapper() {
        return this.interfaceInfoMapper;
    }

    @Override
    public List<InterfaceInfo> selectListByRoleId(int roleId) {
        var interfaceInfoIdList = this.roleInfoInterfaceInfoMapper.selectListByRoleId(roleId);
        return this.interfaceInfoMapper.selectListByIdList(interfaceInfoIdList);
    }
}
