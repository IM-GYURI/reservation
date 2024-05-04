package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.member.MemberDto;
import com.zerobase.reservation.dto.member.SignInDto;
import com.zerobase.reservation.dto.member.SignUpDto;
import com.zerobase.reservation.security.TokenProvider;
import com.zerobase.reservation.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원가입, 로그인 컨트롤러
 * 인증 없이 접근 가능
 */
@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    /**
     * 회원 가입
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.ok(memberService.signUp(signUpDto));
    }

    /**
     * 로그인
     * 로그인 시 토큰 발급
     * 헤더의 Authorization = 발급된 토큰
     */
    @PostMapping("/signin")
    public ResponseEntity<MemberDto> signIn(@RequestBody @Valid SignInDto signInDto) {
        MemberDto memberDto = memberService.signIn(signInDto);
        String token = tokenProvider.generateToken(memberDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);

        return ResponseEntity.ok().headers(headers).body(memberDto);
    }
}