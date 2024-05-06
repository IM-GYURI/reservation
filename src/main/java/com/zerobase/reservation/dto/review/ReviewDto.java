package com.zerobase.reservation.dto.review;

import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.ReviewEntity;
import com.zerobase.reservation.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String title;
    private String content;
    private Double score;
    private MemberEntity memberEntity;
    private StoreEntity storeEntity;

    public static ReviewDto fromEntity(ReviewEntity reviewEntity) {
        return ReviewDto.builder()
                .reviewId(reviewEntity.getId())
                .title(reviewEntity.getTitle())
                .content(reviewEntity.getContent())
                .score(reviewEntity.getScore())
                .memberEntity(reviewEntity.getMemberEntity())
                .storeEntity(reviewEntity.getStoreEntity())
                .build();
    }
}
