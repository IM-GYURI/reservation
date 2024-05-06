package com.zerobase.reservation.repository;

import com.zerobase.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByReservationKey(String reservationKey);
    List<ReservationEntity> findAllByStoreEntityIdAndReservationDateOrderByReservationTime(Long storeId, LocalDate reservationDate);
    boolean existsByReservationKey(String reservationKey);
    void deleteByReservationKey(String reservationKey);
}