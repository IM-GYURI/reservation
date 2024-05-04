package com.zerobase.reservation.dto.store;

import com.zerobase.reservation.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private String storeKey;
    private String name;
    private String address;
    private String description;
    private String phone;

    /**
     * StoreEntity -> StoreDto 변환
     */
    public static StoreDto fromEntity(StoreEntity storeEntity) {
        return StoreDto.builder()
                .storeKey(storeEntity.getStoreKey())
                .name(storeEntity.getName())
                .address(storeEntity.getAddress())
                .description(storeEntity.getDescription())
                .phone(storeEntity.getPhone())
                .build();
    }
}