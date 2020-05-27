package org.tubetrue01.usercenter.service;

import org.tubetrue01.usercenter.pojo.InterfaceInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 10:55 上午
 * Description :
 */
public interface InterfaceInfoService extends BaseService<Integer, InterfaceInfo> {
    List<InterfaceInfo> selectListByRoleId(int roleId);
}
