package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.AccessTokenDto;
import com.dadingcoding.web.controller.dto.MemberSignInDto;
import com.dadingcoding.web.security.JwtToken;
import com.dadingcoding.web.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final MemberLoginService memberLoginService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody MemberSignInDto memberSignInDto) {
        JwtToken jwtToken = memberLoginService.signIn(memberSignInDto.getUsername(), memberSignInDto.getPassword());

        return jwtToken;
    }

    @PostMapping("/refresh")
    public AccessTokenDto refreshToken(@RequestBody MemberSignInDto memberSignInDto, @CookieValue("refreshToken") String refreshToken) {
        // Cookie -> RefreshToken 받아서
        // DB상의 Refresh 토큰과 동일 && 만료되지 않았는지 확인 -> 재발급
        String accessToken = memberLoginService.refresh(memberSignInDto.getUsername(), refreshToken);

        return new AccessTokenDto(memberSignInDto.getUsername(), accessToken);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
