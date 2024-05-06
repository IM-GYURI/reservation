package com.zerobase.reservation.entity;

import com.zerobase.reservation.dto.review.UpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

/**
 * Review 엔티티
 * : 리뷰 아이디, 제목, 내용, 점수, 회원 아이디, 매장 아이디
 * 유니크 조건 : 매장 아이디, 예약 날짜, 예약 시간
 */
@Getter
@NoArgsConstructor
@Entity(name = "Review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @ColumnDefault("0.0")
    private Double score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    @Builder
    public ReviewEntity(String title, String content, Double score) {
        this.title = title;
        this.content = content;
        this.score = score;
    }

    /**
     * member와 store 설정
     * @param memberEntity
     * @param storeEntity
     */
    public void addMemberAndStore(MemberEntity memberEntity, StoreEntity storeEntity) {
        this.memberEntity = memberEntity;
        this.storeEntity = storeEntity;
    }

    /**
     * 리뷰 수정
     */
    public void updateReview(UpdateDto.Request request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.score = request.getScore();
    }
}
