package org.tubetrue01.usercenter.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:36 下午
 * Description :
 */
public interface BaseMapper<ID, T> {

    Optional<T> select(T t);

    List<T> selectAll();

    List<T> selectListByList(@Param("param") List<T> t);

}
