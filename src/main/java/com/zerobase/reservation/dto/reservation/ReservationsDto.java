package com.zerobase.reservation.dto.reservation;

import com.zerobase.reservation.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 예약 전체 목록 조회 시 사용되는 Dto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationsDto {
    private String storeName;
    private String reservationKey;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer headCount;
    private String memberName;
    private String memberPhone;

    /**
     * 매장명, 예약 키, 예약 날짜, 예약 시간, 인원, 회원 이름, 회원 전화번호
     */
    public static ReservationsDto fromEntity(ReservationEntity reservationEntity) {
        return ReservationsDto.builder()
                .storeName(reservationEntity.getStoreEntity().getName())
                .reservationKey(reservationEntity.getReservationKey())
                .reservationDate(reservationEntity.getReservationDate())
                .reservationTime(reservationEntity.getReservationTime())
                .headCount(reservationEntity.getHeadCount())
                .memberName(reservationEntity.getMemberEntity().getName())
                .memberPhone(reservationEntity.getMemberEntity().getPhone())
                .build();
    }
}