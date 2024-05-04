package com.zerobase.reservation.dto.store;

import com.zerobase.reservation.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매장 등록용 Dto
 */
public class RegistrationDto {
    /**
     * 매장 등록 요청 시
     * : 멤버 키, 매장명, 주소, 설명, 전화번호
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String memberKey;

        @NotBlank
        private String storeName;

        @NotNull
        private String address;

        @NotBlank
        private String description;

        @NotBlank
        private String phone;

        public StoreEntity toEntity(String storeKey) {
            return StoreEntity.builder()
                    .storeKey(storeKey)
                    .name(storeName)
                    .address(address)
                    .description(description)
                    .phone(phone)
                    .build();
        }
    }

    /**
     * 매장 등록 완료 후 응답 시
     * : 매장 키, 매장명
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String storeKey;
        private String storeName;

        public static Response from(StoreDto storeDto) {
            return Response.builder()
                    .storeKey(storeDto.getStoreKey())
                    .storeName(storeDto.getName())
                    .build();
        }
    }
}