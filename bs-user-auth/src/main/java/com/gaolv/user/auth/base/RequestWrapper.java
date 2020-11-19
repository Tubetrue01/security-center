package com.gaolv.user.auth.base;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 描述：请求对象包装器
 *
 * @author Pengfei.Zhang by 2020/6/25
 */
@Log4j2
public class RequestWrapper extends HttpServletRequestWrapper {
    private byte[] body = new byte[] {};

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        // try {
        // body = RequestUtils.getBodyString(request).getBytes(StandardCharsets.UTF_8);
        // } catch (IOException e) {
        // log.error("获取请求体对象发生异常：", e);
        // }
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() {

        final var bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // Do nothing
            }
        };
    }

}
