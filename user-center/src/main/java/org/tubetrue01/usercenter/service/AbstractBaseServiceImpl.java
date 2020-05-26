package org.tubetrue01.usercenter.service;

import org.tubetrue01.usercenter.mapper.BaseMapper;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:44 下午
 * Description :
 */
public abstract class AbstractBaseServiceImpl<ID, T> implements BaseService<ID, T>{

    public abstract BaseMapper<ID, T> mapper();

    @Override
    public Optional<T> find(T t) {
        return this.mapper().select(t);
    }

    @Override
    public List<T> findAll() {
        return this.mapper().selectAll();
    }

}
