package com.zerobase.reservation.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 로그인 Dto : 이메일, 패스워드
 */
public record SignInDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) { }