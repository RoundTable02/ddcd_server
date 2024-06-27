package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.*;
import com.dadingcoding.web.security.JwtToken;
import com.dadingcoding.web.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class LoginController {

    private final MemberLoginService memberLoginService;

    // email 검증 -> valid -> 회원가입 정보 받아서 save -> login 해서 Token return
    @PostMapping("/signup-validate-email")
    public ValidateEmailResponseDto validateEmail(@RequestBody ValidateEmailRequestDto validateEmailRequestDto) {
        ValidateEmailResponseDto validateEmailResponseDto = memberLoginService.validateEmail(validateEmailRequestDto.getEmail());

        return validateEmailResponseDto;
    }

    @PostMapping("/signup")
    public void signIn(@RequestBody MemberSignInDto memberSignInDto) {
        memberLoginService.save(memberSignInDto.toEntity());
    }

    @PostMapping("/login")
    public JwtToken login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberLoginService.makeToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());
    }

    @PostMapping("/refresh")
    public AccessTokenDto refreshToken(@RequestBody MemberSignInDto memberSignInDto, @CookieValue("refreshToken") String refreshToken) {
        // Cookie -> RefreshToken 받아서
        // DB상의 Refresh 토큰과 동일 && 만료되지 않았는지 확인 -> 재발급
        String accessToken = memberLoginService.refresh(memberSignInDto.getEmail(), refreshToken);

        return new AccessTokenDto(memberSignInDto.getEmail(), accessToken);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
