package org.tubetrue01.usercenter.utils;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 5:22 下午
 * Description :
 */
public enum StatusCode {
    SUCCESS(10010, "请求成功"),
    ERROR(10011, "操作失败"),
    NULL_RESULT(10014, "空结果集"),
    LOGIN_SUCCESS(10028, "登录成功"),
    USER_INVALID(10031, "账户失效"),
    LOGIN_ERROR(10035, "登录失败"),
    LOGOUT_SUCCESS(10038, "注销成功"),
    LOGOUT_ERROR(10039, "注销失败"),
    LOGIN_TIMEOUT(10041, "登陆超时");

    public int code;
    public String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
