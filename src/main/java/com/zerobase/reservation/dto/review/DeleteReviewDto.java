package com.zerobase.reservation.dto.review;

/**
 * 리뷰 삭제 시 필요한 Dto
 */
public record DeleteReviewDto(Long reviewId) {
    public static DeleteReviewDto from(Long reviewId) {
        return new DeleteReviewDto(reviewId);
    }
}