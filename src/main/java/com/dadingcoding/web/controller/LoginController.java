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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인 컨트롤러", description = "로그인 관련 로직을 담당하는 컨트롤러입니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class LoginController {

    private final MemberLoginService memberLoginService;

    // email 검증 -> valid -> 회원가입 정보 받아서 save -> login 해서 Token return
    @Operation(summary = "이메일 유효성 검사", description = "이메일 패턴 유효성과 DB 내 유일성 여부를 판단")
    @PostMapping("/signup-validate-email")
    public ValidateEmailResponseDto validateEmail(@RequestBody ValidateEmailRequestDto validateEmailRequestDto) {
        ValidateEmailResponseDto validateEmailResponseDto = memberLoginService.validateEmail(validateEmailRequestDto.getEmail());

        return validateEmailResponseDto;
    }

    @Operation(summary = "회원가입", description = "가입시 입력한 멤버 정보를 DB에 저장")
    @PostMapping("/signup")
    public ResponseEntity<Response> signIn(@RequestBody MemberSignInDto memberSignInDto) {
        memberLoginService.save(memberSignInDto.toEntity());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "회원가입이 완료되었습니다."));
    }

    @Operation(summary = "로그인", description = "로그인 후 토큰 정보 GET")
    @PostMapping("/login")
    public MemberLoginResponseDto login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberLoginService.makeToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token 정보를 바탕으로 Access Token 재발급")
    @PostMapping("/refresh")
    public AccessTokenDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto, @CookieValue("refreshToken") String refreshToken) {
        // Cookie -> RefreshToken 받아서
        // DB상의 Refresh 토큰과 동일 && 만료되지 않았는지 확인 -> 재발급
        String accessToken = memberLoginService.refresh(refreshTokenRequestDto.getEmail(), refreshToken);

        return new AccessTokenDto(200, accessToken);
    }

    @Operation(summary = "로그아웃", description = "Refresh Token 무효화")
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@AuthenticationPrincipal UserAdaptor userAdaptor) {
//        if (userAdaptor != null) {
            memberLoginService.logout(userAdaptor.getMember());
//        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "로그아웃에 성공하였습니다."));
    }

    @Operation(summary = "테스트", description = "테스트용 로직")
    @PostMapping("/test")
    public String test() {
        return "success";
    }

}
