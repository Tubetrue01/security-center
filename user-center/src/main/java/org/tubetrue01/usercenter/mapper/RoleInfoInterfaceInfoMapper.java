package org.tubetrue01.usercenter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 12:23 下午
 * Description :
 */
@Mapper
public interface RoleInfoInterfaceInfoMapper {
    List<Integer> selectListByRoleId(int roleId);
}
