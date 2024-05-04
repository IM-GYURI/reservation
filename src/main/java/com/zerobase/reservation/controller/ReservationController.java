package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.reservation.ReservationsDto;
import com.zerobase.reservation.dto.reservation.ReserveDto;
import com.zerobase.reservation.dto.reservation.VisitDto;
import com.zerobase.reservation.service.ReservationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RequestMapping("/reserve")
@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * 매장 예약
     */
    @PostMapping
    public ResponseEntity<ReserveDto.Response> reservation(
            @RequestBody @Valid ReserveDto.Request request) {
        return ResponseEntity.ok(ReserveDto.Response.from(
                reservationService.reserve(request)));
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
    @PatchMapping("/visit")
    public ResponseEntity<?> visit(@RequestBody @Valid VisitDto.Request request) {
        return ResponseEntity.ok(reservationService.visit(request));
    }
}