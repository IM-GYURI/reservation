package com.zerobase.reservation.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.reservation.exception.ErrorCode.INVALID_REQUEST;
import static com.zerobase.reservation.exception.ErrorCode.RESOURCE_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        return toResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(
            MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0)
                .getDefaultMessage();

        return toResponse(INVALID_REQUEST, message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {
        return toResponse(RESOURCE_NOT_FOUND, RESOURCE_NOT_FOUND.getMessage());
    }

    private static ResponseEntity<ErrorResponse> toResponse(
            ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode, message));
    }
}