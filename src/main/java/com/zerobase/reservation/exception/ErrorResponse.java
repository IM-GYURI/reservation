package com.zerobase.reservation.exception;

public record ErrorResponse(
        ErrorCode errorCode,
        String message
) { }