package com.zerobase.reservation.dto.reservation;

import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.ReservationEntity;
import com.zerobase.reservation.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

/**
 * 예약 시 필요한 Dto
 */
public class ReserveDto {
    /**
     * 예약 요청 시
     * : 멤버 키, 매장 키, 예약 날짜, 예약 시간, 인원
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String memberKey;

        @NotBlank
        private String storeKey;

        @DateTimeFormat(iso = DATE)
        private LocalDate reservationDate;

        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime reservationTime;

        @NotNull
        private int headCount;

        public ReservationEntity toEntity(String reservationKey, MemberEntity memberEntity,
                                          StoreEntity storeEntity) {
            ReservationEntity reservationEntity = ReservationEntity.builder()
                    .reservationKey((reservationKey))
                    .reservationDate(reservationDate)
                    .reservationTime(reservationTime)
                    .headCount(headCount)
                    .build();

            reservationEntity.addMemberAndStore(memberEntity, storeEntity);

            return reservationEntity;
        }
    }

    /**
     * 예약 완료 후 응답 시
     * : 매장명, 매장 키, 예약 날짜, 예약 시간, 인원
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String storeName;
        private String storeKey;
        private LocalDate reservationDate;
        private LocalTime reservationTime;
        private int headCount;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .storeName(reservationDto.getStoreEntity().getName())
                    .storeKey(reservationDto.getStoreEntity().getStoreKey())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .headCount(reservationDto.getHeadCount())
                    .build();
        }
    }
}