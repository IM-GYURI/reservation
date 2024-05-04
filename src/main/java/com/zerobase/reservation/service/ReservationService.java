package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.reservation.ReservationDto;
import com.zerobase.reservation.dto.reservation.ReservationsDto;
import com.zerobase.reservation.dto.reservation.ReserveDto;
import com.zerobase.reservation.dto.reservation.VisitDto;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.ReservationEntity;
import com.zerobase.reservation.entity.StoreEntity;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.StoreRepository;
import com.zerobase.reservation.util.KeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.reservation.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final MemberService memberService;
    private final StoreService storeService;

    /**
     * 매장 예약
     * 매장과 회원 조회 후 해당 매장에 예약 진행
     */
    @Transactional
    public ReservationDto reserve(ReserveDto.Request request) {
        StoreEntity storeEntity = storeService.findByStoreKeyOrThrow(request.getStoreKey());
        MemberEntity memberEntity = memberService.findByMemberKeyOrThrow(request.getMemberKey());

        return ReservationDto.fromEntity(reservationRepository.save(
                request.toEntity(KeyGenerator.generateKey(), memberEntity, storeEntity)));
    }

    /**
     날짜별 예약 조회
     */
    public List<ReservationsDto> getReservationListByDate(Long storeId, LocalDate date) throws CustomException {
        if (!storeRepository.existsById(storeId)) {
            throw new CustomException(STORE_NOT_FOUND);
        }

        return reservationRepository.findAllByStoreEntityIdAndReservationDateOrderByReservationTime(storeId, date)
                .stream()
                .map(reservationEntity -> ReservationsDto.builder()
                        .storeName(reservationEntity.getStoreEntity().getName())
                        .reservationKey(reservationEntity.getReservationKey())
                        .reservationDate(reservationEntity.getReservationDate())
                        .reservationTime(reservationEntity.getReservationTime())
                        .headCount(reservationEntity.getHeadCount())
                        .memberName(reservationEntity.getMemberEntity().getName())
                        .memberPhone(reservationEntity.getMemberEntity().getPhone())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 방문 확인
     * 예약 키를 통해 예약 존재 여부 확인 후, 예약 시간 10분 전이라면 방문 확인 진행
     */
    @Transactional
    public VisitDto.Response visit(VisitDto.Request request) {
        String reservationKey = request.getReservationKey();

        ReservationEntity reservationEntity = reservationRepository.findByReservationKey(reservationKey)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validateVisit(reservationEntity);

        reservationEntity.updateVisit();

        return new VisitDto.Response(reservationEntity.getReservationKey());
    }

    /**
     * 예약 시간 10분 전인지 & 아직 방문하지 않은게 맞는지 여부 확인
     */
    private static void validateVisit(ReservationEntity reservationEntity) {
        if (LocalTime.now().isAfter(reservationEntity.getReservationTime()
                .minus(Duration.ofMinutes(10)))) {
            throw new CustomException(ARRIVAL_TIME_EXCEED);
        }

        if (reservationEntity.isVisited()) {
            throw new CustomException(ALREADY_ARRIVAL);
        }
    }
}