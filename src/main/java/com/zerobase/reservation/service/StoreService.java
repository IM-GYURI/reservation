package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.store.EditDto;
import com.zerobase.reservation.dto.store.RegistrationDto;
import com.zerobase.reservation.dto.store.StoreDto;
import com.zerobase.reservation.dto.store.StoresDto;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.StoreEntity;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.repository.StoreRepository;
import com.zerobase.reservation.util.KeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.reservation.exception.ErrorCode.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberService memberService;

    /**
     * 매장 등록
     * 멤버 키를 통해 멤버 정보를 찾은 후 매장 등록 진행
     */
    @Transactional
    public StoreDto registration(RegistrationDto.Request request) {
        MemberEntity memberEntity = memberService.findByMemberKeyOrThrow(request.getMemberKey());

        StoreEntity storeEntity = request.toEntity(
                KeyGenerator.generateKey());

        storeEntity.addMember(memberEntity);
        storeRepository.save(storeEntity);

        return StoreDto.fromEntity(storeEntity);
    }

    /**
     * 키워드를 통한 매장명 자동완성 검색
     */
    public List<String> searchKeyword(String keyword) {
        Pageable limit = PageRequest.of(0, 50);
        return storeRepository.findAllByNameContains(keyword, limit)
                .stream()
                .map(StoreEntity::getName)
                .toList();
    }

    /**
     * 매장 상세 정보 조회
     */
    public StoreDto information(String storeKey) {
        return StoreDto.fromEntity(findByStoreKeyOrThrow(storeKey));
    }

    /**
     * 매장 키를 통해 매장이 존재하는지 확인
     */
    public StoreEntity findByStoreKeyOrThrow(String storeKey) {
        return storeRepository.findByStoreKey(storeKey)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    /**
     * 매장 전체 목록 조회 (가나다순)
     */
    public List<StoresDto> getAllSortedStores() {
        List<StoreEntity> storeList = storeRepository.findAll();

        return storeList.stream()
                .sorted(Comparator.comparing(StoreEntity::getName))
                .map(storeEntity -> new StoresDto(storeEntity.getName(), storeEntity.getAddress()))
                .collect(Collectors.toList());
    }

    /**
     * 매장 정보 수정
     */
    @Transactional
    public StoreDto edit(EditDto editDto) {
        StoreEntity storeEntity = findByStoreKeyOrThrow(editDto.getStoreKey());

        storeEntity.updateStore(editDto);

        return StoreDto.fromEntity(storeEntity);
    }

    /**
     * 매장 삭제
     * 매장 검증 후 삭제 진행
     */
    @Transactional
    public String delete(String storeKey) {
        validateStoreExists(storeKey);

        storeRepository.deleteByStoreKey(storeKey);

        return storeKey;
    }

    /**
     * 매장 키를 통해 매장 검증
     */
    private void validateStoreExists(String storeKey) {
        if (!storeRepository.existsByStoreKey(storeKey)) {
            throw new CustomException(STORE_NOT_FOUND);
        }
    }
}