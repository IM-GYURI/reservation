package com.zerobase.reservation.repository;

import com.zerobase.reservation.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<MemberEntity> findByMemberKey(String memberKey);
    Optional<MemberEntity> findByPhone(String phoneNumber);
}