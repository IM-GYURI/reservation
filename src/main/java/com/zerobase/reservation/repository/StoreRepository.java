package com.zerobase.reservation.repository;

import com.zerobase.reservation.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findByStoreKey(String storeKey);
    boolean existsById(Long storeId);
    boolean existsByStoreKey(String storeKey);
    void deleteByStoreKey(String storeKey);
    Page<StoreEntity> findAllByNameContains(String name, Pageable pageable);
}