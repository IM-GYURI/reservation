package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Member
    MEMBER_NOT_FOUND(NOT_FOUND, "회원을 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(BAD_REQUEST, "이미 존재하는 회원입니다."),

    // Store
    STORE_NOT_FOUND(NOT_FOUND, "매장을 찾을 수 없습니다"),

    // Reservation
    ALREADY_RESERVED(NOT_FOUND, "이미 예약된 일시입니다."),
    RESERVATION_NOT_FOUND(NOT_FOUND, "예약을 찾을 수 없습니다."),
    ARRIVAL_TIME_EXCEED(BAD_REQUEST, "도착 시간이 지났습니다."),
    ALREADY_ARRIVAL(BAD_REQUEST, "이미 도착 확인 되었습니다."),
    RESERVATION_NOT_VISITED(BAD_REQUEST, "방문한 예약이 아닙니다."),
    RESERVATION_ACCESS_DENY(FORBIDDEN, "해당 예약에 접근할 수 없습니다."),

    // Review
    REVIEW_NOT_FOUND(NOT_FOUND, "리뷰를 찾을 수 없습니다."),
    REVIEW_ACCESS_DENY(FORBIDDEN, "해당 리뷰에 접근할 수 없습니다."),

    // Global
    RESOURCE_NOT_FOUND(NOT_FOUND, "요청한 자원을 찾을 수 없습니다."),
    INVALID_REQUEST(BAD_REQUEST, "올바르지 않은 요청입니다.");

    private final HttpStatus status;
    private final String message;
}