package com.zerobase.reservation.dto.store;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매장 정보 수정용 Dto
 * : 매장 키, 매장명, 주소, 설명, 전화번호
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditDto {
    @NotBlank
    private String storeKey;

    @NotBlank
    private String storeName;

    @NotBlank
    private String address;

    @NotBlank
    private String description;

    @NotBlank
    private String phone;
}
