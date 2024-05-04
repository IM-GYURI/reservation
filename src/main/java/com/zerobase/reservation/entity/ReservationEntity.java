package com.zerobase.reservation.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Reservation 엔티티
 * : 예약 아이디, 예약 키, 예약 날짜, 예약 시간, 인원, 방문 여부 (기본 false), 회원 아이디, 매장 아이디
 * 유니크 조건 : 매장 아이디, 예약 날짜, 예약 시간
 */
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"store_id", "reservation_date", "reservation_time"})})
@Entity(name = "Reservation")
public class ReservationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reservationKey;

    @Column(nullable = false, name = "reservation_date")
    private LocalDate reservationDate;

    @Column(nullable = false, name = "reservation_time")
    private LocalTime reservationTime;

    @Column(nullable = false)
    private Integer headCount;

    @Column(nullable = false)
    private boolean isVisited = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    @Builder
    public ReservationEntity(String reservationKey, LocalDate reservationDate,
                             LocalTime reservationTime, Integer headCount) {
        this.reservationKey = reservationKey;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.headCount = headCount;
    }

    /**
     * 회원과 매장 정보 추가
     */
    public void addMemberAndStore(MemberEntity memberEntity, StoreEntity storeEntity) {
        this.memberEntity = memberEntity;
        this.storeEntity = storeEntity;
    }

    /**
     * 방문 여부 업데이트 : false -> true
     */
    public void updateVisit() {
        this.isVisited = true;
    }
}