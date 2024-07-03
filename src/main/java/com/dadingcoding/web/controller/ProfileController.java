package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.response.ExceptResponse;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ProfileController {
    private final MemberService memberService;

    @GetMapping("/profile")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal UserAdaptor userAdaptor) {
        try {
            Member member = userAdaptor.getMember();
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal UserAdaptor userAdaptor,
                                           @RequestBody UpdateProfileRequest request) {
        try {
            Member member = userAdaptor.getMember();

            // 프로필 정보 업데이트
            member.setUsername(request.getUsername());
            member.setEmail(request.getEmail());
            member.setPhone(request.getPhone());

            // 업데이트된 회원 정보 저장
            memberService.updateMember(member);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "프로필이 성공적으로 업데이트되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }
}
