package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.member.MemberDto;
import com.zerobase.reservation.dto.member.SignInDto;
import com.zerobase.reservation.dto.member.SignUpDto;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.repository.MemberRepository;
import com.zerobase.reservation.util.KeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.reservation.exception.ErrorCode.MEMBER_ALREADY_EXISTS;
import static com.zerobase.reservation.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final KeyGenerator keyGenerator;

    /**
     * 회원가입 기능
     * 이메일을 통해 이미 등록된 회원인지 여부를 확인한 후 진행
     */
    @Transactional
    public MemberDto signUp(SignUpDto signUpDto) {
        validateMemberExists(signUpDto.getEmail());

        MemberEntity savedMember = memberRepository.save(
                signUpDto.toEntity(
                        keyGenerator.generateKey(),
                        passwordEncoder.encode(signUpDto.getPassword())
                )
        );

        return MemberDto.fromEntity(savedMember);
    }

    /**
     * 로그인 기능
     * 로그인 요청 정보를 spring security의 authenticate 메소드를 사용하여 검증
     * 이메일과 비밀번호를 검증하여 확인
     */
    public MemberDto signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInDto.email(), signInDto.password()
                ));

        return MemberDto.fromEntity((MemberEntity) authentication.getPrincipal());
    }

    /**
     * 이메일을 통해 이미 존재하는 회원인지 여부 확인
     */
    private void validateMemberExists(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(MEMBER_ALREADY_EXISTS);
        }
    }

    /**
     * 멤버 키를 통해 회원이 존재하는지 확인
     */
    public MemberEntity findByMemberKeyOrThrow(String memberKey) {
        return memberRepository.findByMemberKey(memberKey)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    /**
     * 전화번호를 통해 회원이 존재하는지 확인
     */
    public MemberEntity findByPhoneOrThrow(String phoneNumber) {
        return memberRepository.findByPhone(phoneNumber)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}