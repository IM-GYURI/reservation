package com.zerobase.reservation.type;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 회원의 역할 타입 : Manager/Customer
 */
@Getter
@RequiredArgsConstructor
public enum Role {
    MANAGER("ROLE_MANAGER"),
    CUSTOMER("ROLE_CUSTOMER");

    private final String key;

    /**
     * 키를 통해 올바른 권한인지 확인
     */
    public static Role fromKey(String key) {
        return Arrays.stream(values())
                .filter(o -> o.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new CustomException(
                        ErrorCode.INVALID_REQUEST, "잘못된 권한 타입입니다."
                ));
    }
}