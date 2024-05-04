package com.zerobase.reservation.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매장 전체 목록 조회 시 매장명과 주소만 보이도록 하는 Dto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoresDto {
    private String storeName;
    private String address;
}