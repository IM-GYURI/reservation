package com.zerobase.reservation.dto.review;

import com.zerobase.reservation.entity.ReservationEntity;
import com.zerobase.reservation.entity.ReviewEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WriteDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String reservationKey;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotNull
        private Double score;

        public ReviewEntity toEntity(ReservationEntity reservationEntity) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .title(title)
                    .content(content)
                    .score(score)
                    .build();

            reviewEntity.addMemberAndStore(reservationEntity.getMemberEntity(),
                    reservationEntity.getStoreEntity());

            return reviewEntity;
        }
    }

    public record Response(Long reviewId) {
        public static Response from(ReviewDto reviewDto) {
            return new Response(reviewDto.getReviewId());
        }
    }
}
