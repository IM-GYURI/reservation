package com.zerobase.reservation.entity;

import com.zerobase.reservation.dto.review.UpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    public void addMemberAndStore(MemberEntity memberEntity, StoreEntity storeEntity) {
        this.memberEntity = memberEntity;
        this.storeEntity = storeEntity;
    }

    public void updateReview(UpdateDto.Request request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.score = request.getScore();
    }
}
