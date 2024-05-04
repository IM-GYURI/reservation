package com.zerobase.reservation.entity;

import com.zerobase.reservation.dto.store.EditDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Store 엔티티
 * : 아이디, 매장 키, 매장명, 주소, 설명, 전화번호, 멤버 아이디(FK)
 */
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "store_key")})
@Entity(name = "Store")
public class StoreEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "store_key")
    private String storeKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Builder
    public StoreEntity(String storeKey, String name, String address, String description, String phone) {
        this.storeKey = storeKey;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
    }

    /**
     * 멤버 설정
     */
    public void addMember(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    /**
     * 매장 정보 수정
     */
    public void updateStore(EditDto editDto) {
        this.name = editDto.getStoreName();
        this.address = editDto.getAddress();
        this.description = editDto.getDescription();
        this.phone = editDto.getPhone();
    }
}