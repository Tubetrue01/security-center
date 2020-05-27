package org.tubetrue01.usercenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tubetrue01.usercenter.pojo.InterfaceInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 10:54 上午
 * Description :
 */
@Mapper
public interface InterfaceInfoMapper extends BaseMapper<Integer, InterfaceInfo> {
    List<InterfaceInfo> selectListByIdList(@Param("param") List<Integer> idList);
}
