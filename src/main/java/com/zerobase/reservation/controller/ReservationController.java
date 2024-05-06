package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.reservation.DeleteDto;
import com.zerobase.reservation.dto.reservation.ReservationsDto;
import com.zerobase.reservation.dto.reservation.ReserveDto;
import com.zerobase.reservation.dto.reservation.VisitDto;
import com.zerobase.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/reserve")
@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * 매장 예약
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ReserveDto.Response> reservation(
            @RequestBody @Valid ReserveDto.Request request) {
        return ResponseEntity.ok(ReserveDto.Response.from(
                reservationService.reserve(request)));
    }

    /**
     * 예약 취소
     */
    @DeleteMapping("/{reservationKey}")
    public ResponseEntity<DeleteDto> delete(@PathVariable String reservationKey) {
        return ResponseEntity.ok(new DeleteDto(reservationService.delete(reservationKey)));
    }

    /**
     날짜별 예약 조회
     */
    @GetMapping("{id}/{date}")
    public ResponseEntity<List<ReservationsDto>> reservations(
            @PathVariable Long id, @PathVariable LocalDate date
    ) {
        return ResponseEntity.ok(reservationService.getReservationListByDate(id, date));
    }

    /**
     * 방문 확인
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/visit")
    public ResponseEntity<?> visit(@RequestBody @Valid VisitDto.Request request) {
        return ResponseEntity.ok(reservationService.visit(request));
    }
}