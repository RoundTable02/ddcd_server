package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.UpdateProfileRequest;
import com.dadingcoding.web.controller.dto.response.GetProfileResponseDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ProfileController {
    private final MemberService memberService;

    @GetMapping("/profile")
    public GetProfileResponseDto getMyProfile(@AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        return GetProfileResponseDto.toDto(member);
    }

    @PutMapping("/profile")
    public ResponseEntity<Response> updateProfile(@AuthenticationPrincipal UserAdaptor userAdaptor,
                                           @RequestBody UpdateProfileRequest request) {
        memberService.updateProfile(userAdaptor.getMember(), request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로필이 수정되었습니다."));
    }
}
