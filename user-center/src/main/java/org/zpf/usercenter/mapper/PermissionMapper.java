package org.zpf.usercenter.mapper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/24
 * Time : 5:31 下午
 * Description :
 */
@Component
public class PermissionMapper {
    // TODO will be instead by DAO in the future
    public Map<String, List<String>> permission = new HashMap<>(){{
        put("admin", List.of("/user/info", "/user/test"));
        put("test", List.of("/user/info"));
    }};
}
