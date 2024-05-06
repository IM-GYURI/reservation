package com.zerobase.reservation.dto.store;

import java.util.List;

/**
 * 매장 조회 시 필요한 Dto
 */
public record SearchDto(List<String> storeNames) {
}