package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.UpdateProfileRequest;
import com.dadingcoding.web.controller.dto.response.GetProfileResponseDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Tag(name = "프로필 컨트롤러", description = "멤버 프로필 관련 로직을 담당하는 컨트롤러입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ProfileController {
    private final MemberService memberService;

    @Operation(summary = "프로필 조회", description = "자기 자신의 정보 GET")
    @GetMapping("/profile")
    public GetProfileResponseDto getMyProfile(@AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        return GetProfileResponseDto.toDto(member);
    }

    @Operation(summary = "프로필 수정", description = "자기 자신의 정보 수정")
    @PutMapping("/profile")
    public ResponseEntity<Response> updateProfile(@AuthenticationPrincipal UserAdaptor userAdaptor,
                                           @RequestBody UpdateProfileRequest request) {
        memberService.updateProfile(userAdaptor.getMember(), request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로필이 수정되었습니다."));
    }
}
