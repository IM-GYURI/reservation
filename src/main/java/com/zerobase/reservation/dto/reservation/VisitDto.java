package com.zerobase.reservation.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

/**
 * 방문 확인 시 필요한 Dto
 */
public class VisitDto {
    /**
     * 방문 확인 요청 시
     * : 전화번호, 매장 키, 예약 날짜, 예약 시간, 예약 키
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String phone;

        @NotBlank
        private String storeKey;

        @DateTimeFormat(iso = DATE)
        private LocalDate reservationDate;

        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime reservationTime;

        @NotBlank
        private String reservationKey;
    }

    public record Response(
            String reservationKey
    ) {}
}