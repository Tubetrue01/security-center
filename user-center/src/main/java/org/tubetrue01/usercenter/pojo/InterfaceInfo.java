package org.tubetrue01.usercenter.pojo;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/26
 * Time : 4:24 下午
 * Description :
 */
@Data
public class InterfaceInfo {
    private Integer id;              // 主键
    private String interfaceUrl;     // 接口地址
    private String method;           // 请求方法
}
