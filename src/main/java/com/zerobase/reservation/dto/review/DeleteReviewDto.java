package com.zerobase.reservation.dto.review;

public record DeleteReviewDto(Long reviewId) {
    public static DeleteReviewDto from(Long reviewId) {
        return new DeleteReviewDto(reviewId);
    }
}