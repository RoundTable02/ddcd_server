package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.response.ExceptResponse;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MypageController {
    private final MemberService memberService;

    @GetMapping("/profile")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal UserAdaptor userAdaptor) {
        try {
            Member member = userAdaptor.getMember();

            // Member 객체를 JSON 형태로 변환하여 반환
            Map<String, Object> profileResponse = new HashMap<>();
            profileResponse.put("userId", member.getId());
            profileResponse.put("username", member.getUsername());
            profileResponse.put("email", member.getEmail());
            profileResponse.put("phone", member.getEmail());
            profileResponse.put("role", member.getRole().toString());

            // 성공적인 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, profileResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateMyProfile(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody UpdateProfileRequest request) {
        try {
            Member member = userAdaptor.getMember();

            // 사용자 정보 업데이트
            memberService.updateProfile(member.getId(), request);

            // 성공적인 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "프로필이 성공적으로 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }


}

