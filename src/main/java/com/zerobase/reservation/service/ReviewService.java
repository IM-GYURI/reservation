package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.review.ReviewDto;
import com.zerobase.reservation.dto.review.UpdateDto;
import com.zerobase.reservation.dto.review.WriteDto;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.ReservationEntity;
import com.zerobase.reservation.entity.ReviewEntity;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.ReviewRepository;
import com.zerobase.reservation.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.reservation.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final MemberService memberService;

    /**
     * 리뷰 작성
     * 리뷰 작성을 요청한 예약에 대해 방문 완료 여부 확인
     * 예약자와 리뷰 요청자가 같은지 확인 후 작성 가능
     */
    @Transactional
    public ReviewDto write(WriteDto.Request request, UserDetails userDetails) {
        ReservationEntity reservationEntity = reservationRepository.findByReservationKey(request.getReservationKey())
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validateReservation(reservationEntity, userDetails);

        return ReviewDto.fromEntity(
                reviewRepository.save(request.toEntity(reservationEntity)));
    }

    /**
     * 방문여부와 동일인 검증
     */
    private static void validateReservation(ReservationEntity reservationEntity, UserDetails userDetails) {
        if (!reservationEntity.isVisited()) {
            throw new CustomException(RESERVATION_NOT_VISITED);
        }

        if (!reservationEntity.getMemberEntity().getEmail().equals(userDetails.getUsername())) {
            System.out.println(reservationEntity.getMemberEntity().getEmail());
            System.out.println(userDetails.getUsername());
            throw new CustomException(RESERVATION_ACCESS_DENY);
        }
    }

    /**
     * 리뷰 수정
     * 수정 요청 멤버와 리뷰 작성 멤버가 다를 경우 리뷰 수정 불가
     * 회원을 조회하는 쿼리를 대신하여 인증된 사용자의 정보로 검증
     */
    @PostAuthorize("isAuthenticated() " +
    "and returnObject.memberEntity.email == authentication.principal.username")
    @Transactional
    public ReviewDto update(UpdateDto.Request request) {
        ReviewEntity reviewEntity = findByIdOrThrow(request.getReviewId());
        reviewEntity.updateReview(request);
        return ReviewDto.fromEntity(reviewEntity);
    }

    private ReviewEntity findByIdOrThrow(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));
    }

    /**
     * 리뷰 삭제
     * 로그인한 사용자가 CUSTOMER일 경우, 리뷰 작성자와 동일인인지 여부 확인
     * 로그인한 사용자가 MANAGER일 경우, 점주와 동일인인지 여부 확인
     */
    @Transactional
    public Long delete(Long reviewId, UserDetails userDetails) {
        ReviewEntity reviewEntity = findByIdOrThrow(reviewId);

        validateMember(userDetails, reviewEntity);

        reviewRepository.deleteById(reviewId);

        return reviewId;
    }

    private void validateMember(UserDetails userDetails, ReviewEntity reviewEntity) {
        MemberEntity principal =
                memberService.findByEmailOrThrow(userDetails.getUsername());

        if (principal.getRole() == Role.MANAGER) {
            if (!reviewEntity.getStoreEntity().getMemberEntity().getEmail()
                    .equals(principal.getEmail())) {
                throw new CustomException(REVIEW_ACCESS_DENY);
            }
        } else {
            if (!reviewEntity.getMemberEntity().getEmail().equals(principal.getEmail())) {
                throw new CustomException(REVIEW_ACCESS_DENY);
            }
        }
    }
}
