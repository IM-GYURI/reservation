package com.zerobase.reservation.dto.reservation;

import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.ReservationEntity;
import com.zerobase.reservation.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private String reservationKey;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int headCount;
    private boolean isVisited;
    private MemberEntity memberEntity;
    private StoreEntity storeEntity;

    /**
     * ReservationEntity -> ReservationDto 변환
     */
    public static ReservationDto fromEntity(ReservationEntity reservationEntity) {
        return ReservationDto.builder()
                .reservationKey(reservationEntity.getReservationKey())
                .reservationDate(reservationEntity.getReservationDate())
                .reservationTime(reservationEntity.getReservationTime())
                .headCount(reservationEntity.getHeadCount())
                .isVisited(reservationEntity.isVisited())
                .memberEntity(reservationEntity.getMemberEntity())
                .storeEntity(reservationEntity.getStoreEntity())
                .build();
    }
}