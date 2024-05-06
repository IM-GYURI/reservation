package com.zerobase.reservation.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 리뷰 수정 시 필요한 Dto
 */
public class UpdateDto {
    /**
     * 요청 시 : 리뷰 아이디, 제목, 내용, 점수
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long reviewId;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotNull
        private Double score;
    }

    /**
     * 응답 시 : 리뷰 아이디
     */
    public record Response(Long reviewId) {
        public static Response from(ReviewDto reviewDto) {
            return new Response(reviewDto.getReviewId());
        }
    }
}
