package com.zerobase.reservation.dto.member;

import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.type.Role;
import com.zerobase.reservation.util.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 Dto : 이름, 이메일, 비밀번호, 전화번호, 역할
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @ValidEnum(enumClass = Role.class)
    private Role role;

    /**
     * 멤버 키와 비밀번호를 받아 MemberEntity 빌드
     */
    public MemberEntity toEntity(String memberKey, String password) {
        return MemberEntity.builder()
                .memberKey(memberKey)
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .role(role)
                .build();
    }
}