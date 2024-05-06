package com.zerobase.reservation.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateDto {
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

    public record Response(Long reviewId) {
        public static Response from(ReviewDto reviewDto) {
            return new Response(reviewDto.getReviewId());
        }
    }
}
