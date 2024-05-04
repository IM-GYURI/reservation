package com.zerobase.reservation.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 인조 식별자 생성을 위한 UUID 생성 객체
 */
@Component
public class KeyGenerator {
    public static String generateKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}