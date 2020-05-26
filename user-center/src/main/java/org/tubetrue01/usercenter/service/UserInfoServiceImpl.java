package org.tubetrue01.usercenter.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tubetrue01.usercenter.mapper.BaseMapper;
import org.tubetrue01.usercenter.mapper.UserInfoMapper;
import org.tubetrue01.usercenter.pojo.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:48 下午
 * Description :
 */
@Log4j2
@Service
public class UserInfoServiceImpl extends AbstractBaseServiceImpl<Integer, UserInfo> implements UserInfoService, UserDetailsService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public BaseMapper<Integer, UserInfo> mapper() {
        return this.userInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("-======Enter userInfoServiceImpl ======-");
        var user = new UserInfo();
        user.setUsername(username);
        var userInfo = this.userInfoMapper.select(user).get();
        return userInfo;
    }
}
