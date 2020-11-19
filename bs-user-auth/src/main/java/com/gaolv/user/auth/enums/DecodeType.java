package com.gaolv.user.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：
 *
 * @author chenpan by 2020/10/14
 */
@Getter
@AllArgsConstructor
public enum DecodeType {
    /** AES加密 */
    AES((byte)0),
    /** RSA加密 */
    RSA((byte)1);

    private byte code;
}
