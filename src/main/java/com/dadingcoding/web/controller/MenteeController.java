package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mentee")
public class MenteeController {

    private final MenteeService menteeService;

    @PostMapping("application")
    public ResponseEntity<Response> addApplication(@RequestBody AddApplicationRequestDto request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();

        if(member.getRole() != Role.MENTEE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new Response(403, "권한이 없는 접근"));
        }

        menteeService.addApplication(member, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "지원서가 제출되었습니다."));
    }

}
