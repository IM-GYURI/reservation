package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.store.*;
import com.zerobase.reservation.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/store")
@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;

    /**
     * 매장 등록
     * MANAGER 권한을 가지고 있을 경우만 사용 가능
     */
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/registration")
    public ResponseEntity<RegistrationDto.Response> registration(
            @RequestBody @Valid RegistrationDto.Request request) {
        StoreDto storeDto = storeService.registration(request);

        return ResponseEntity.ok(RegistrationDto.Response.from(storeDto));
    }

    /**
     * 매장명 자동완성 검색
     */
    @GetMapping("/search")
    public ResponseEntity<SearchDto> searchKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(new SearchDto(storeService.searchKeyword(keyword)));
    }

    /**
     * 매장 키를 통한 매장 정보 조회
     */
    @GetMapping("/{storeKey}")
    public ResponseEntity<StoreDto> information(@PathVariable String storeKey) {
        return ResponseEntity.ok(storeService.information(storeKey));
    }

    /**
     * 매장 전체 목록 조회 (가나다순)
     */
    @GetMapping("/sortedlist")
    public ResponseEntity<List<StoresDto>> sortedList() {
        List<StoresDto> storesList = storeService.getAllSortedStores();

        return ResponseEntity.ok(storesList);
    }

    /**
     * 매장 정보 수정
     * MANAGER 권한을 가지고 있을 경우만 사용 가능
     */
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping
    public ResponseEntity<StoreDto> edit(@RequestBody @Valid EditDto editDto) {
        return ResponseEntity.ok(storeService.edit(editDto));
    }

    /**
     * 매장 삭제
     * MANAGER 권한을 가지고 있을 경우만 사용 가능
     */
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{storeKey}")
    public ResponseEntity<DeleteStoreDto> delete(@PathVariable String storeKey) {
        return ResponseEntity.ok(new DeleteStoreDto(storeService.delete(storeKey)));
    }
}