package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.review.DeleteReviewDto;
import com.zerobase.reservation.dto.review.UpdateDto;
import com.zerobase.reservation.dto.review.WriteDto;
import com.zerobase.reservation.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     *  리뷰 작성
     */
    @PostMapping
    public ResponseEntity<WriteDto.Response> write(
            @RequestBody @Valid WriteDto.Request request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(WriteDto.Response.from(
                reviewService.write(request, userDetails)
        ));
    }

    /**
     * 리뷰 수정
     */
    @PatchMapping
    public ResponseEntity<UpdateDto.Response> update(
            @RequestBody @Valid UpdateDto.Request request) {
        return ResponseEntity.ok(UpdateDto.Response.from(
                reviewService.update(request)));
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<DeleteReviewDto> delete(@PathVariable Long reviewId,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(DeleteReviewDto.from(
                reviewService.delete(reviewId, userDetails)));
    }
}
