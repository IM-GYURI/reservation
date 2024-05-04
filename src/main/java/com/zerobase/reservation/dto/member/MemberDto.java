package com.zerobase.reservation.dto.member;

import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String memberKey;
    private String name;
    private String email;
    private Role role;

    /**
     * MemberEntity -> MemberDto 변환
     */
    public static MemberDto fromEntity(MemberEntity memberEntity) {
        return MemberDto.builder()
                .memberKey(memberEntity.getMemberKey())
                .name(memberEntity.getName())
                .email(memberEntity.getEmail())
                .role(memberEntity.getRole())
                .build();
    }
}