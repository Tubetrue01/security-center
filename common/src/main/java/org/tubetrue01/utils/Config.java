package org.tubetrue01.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tubetrue01.configuration.SecurityConfigProperties;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2020/6/1
 * Time : 7:51 下午
 * Description :
 */
@Log4j2
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Config {
    private static SecurityConfigProperties securityConfigProperties;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Cache {
        private static final Map<String, Reference<Object>> CACHE = new ConcurrentHashMap<>(8);
        public static final boolean ENABLE = getSecurityConfigProperties().getClient().isCache();

        public static Object get(String key) {
            return CACHE.get(key).get();
        }

        public static boolean isNull(String key) {
            return CACHE.get(key) == null || CACHE.get(key).get() == null;
        }

        public static void put(String key, Object value) {
            CACHE.put(key, new WeakReference<>(value));
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Security {
        public static final String JWT_SIGNING_KEY = getSecurityConfigProperties().getJwtSigningKey();
        public static final String JWT_PARAM_IN_HEADER = getSecurityConfigProperties().getJwtParamInHeader();
        public static final String TOKEN_PARAM_IN_header = getSecurityConfigProperties().getTokenParamInHeader();
        public static final long TOKEN_EXPIRED_TIME = getSecurityConfigProperties().getTokenExpiredTime();
        public static final long JWT_EXPIRED_TIME = getSecurityConfigProperties().getJwtExpiredTime();

    }

    @Autowired
    public void setSecurityConfigProperties(SecurityConfigProperties securityConfigProperties) {
        Config.securityConfigProperties = securityConfigProperties;
    }

    private static SecurityConfigProperties getSecurityConfigProperties() {
        return Config.securityConfigProperties;
    }
}
