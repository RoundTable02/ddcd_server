package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.RefreshTokenRequestDto;
import com.dadingcoding.web.controller.dto.response.MemberLoginResponseDto;
import com.dadingcoding.web.controller.dto.request.MemberLoginDto;
import com.dadingcoding.web.controller.dto.request.MemberSignInDto;
import com.dadingcoding.web.controller.dto.request.ValidateEmailRequestDto;
import com.dadingcoding.web.controller.dto.response.AccessTokenDto;
import com.dadingcoding.web.controller.dto.response.ValidateEmailResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class LoginController {

    private final MemberLoginService memberLoginService;

    // email 검증 -> valid -> 회원가입 정보 받아서 save -> login 해서 Token return
    @PostMapping("/signup-validate-email")
    public ValidateEmailResponseDto validateEmail(@RequestBody ValidateEmailRequestDto validateEmailRequestDto) {
        ValidateEmailResponseDto validateEmailResponseDto = memberLoginService.validateEmail(validateEmailRequestDto.getEmail());

        return validateEmailResponseDto;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signIn(@RequestBody MemberSignInDto memberSignInDto) {
        memberLoginService.save(memberSignInDto.toEntity());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "회원가입이 완료되었습니다."));
    }

    @PostMapping("/login")
    public MemberLoginResponseDto login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberLoginService.makeToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());
    }

    @PostMapping("/refresh")
    public AccessTokenDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto, @CookieValue("refreshToken") String refreshToken) {
        // Cookie -> RefreshToken 받아서
        // DB상의 Refresh 토큰과 동일 && 만료되지 않았는지 확인 -> 재발급
        String accessToken = memberLoginService.refresh(refreshTokenRequestDto.getEmail(), refreshToken);

        return new AccessTokenDto(200, accessToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@AuthenticationPrincipal UserAdaptor userAdaptor) {
//        if (userAdaptor != null) {
            memberLoginService.logout(userAdaptor.getMember());
//        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "로그아웃에 성공하였습니다."));
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

}
