package org.tubetrue01.usercenter.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/5/27
 * Time : 5:23 下午
 * Description :
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultRtn<T> {
    private int code;
    private String msg;
    private PageInfo pageInfo;
    private List<T> dataList;

    @SafeVarargs
    public static <T> ResultRtn<T> of(StatusCode statusCode, PageInfo pageInfo, T... t) {
        var result = new ResultRtn<T>();
        result.setCode(statusCode.code);
        result.setMsg(statusCode.msg);
        result.setPageInfo(pageInfo);
        result.setDataList(List.of(t));
        return result;
    }

    @SafeVarargs
    public static <T> ResultRtn<T> of(StatusCode statusCode, T... t) {
        var result = new ResultRtn<T>();
        result.setCode(statusCode.code);
        result.setMsg(statusCode.msg);
        result.setDataList(List.of(t));
        return result;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PageInfo {

        private final int pageNum;
        private final int pageSize;
        private final long total;

        public static PageInfo of(int pageNum, int pageSize, long total) {
            return new PageInfo(pageNum, pageSize, total);
        }
    }
}
