package org.tubetrue01.usercenter.service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:42 下午
 * Description :
 */
public interface BaseService<ID, T> {
    Optional<T> find(T t);

    List<T> findAll();
}
