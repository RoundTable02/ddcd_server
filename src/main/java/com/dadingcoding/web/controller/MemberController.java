package com.dadingcoding.web.controller;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    private List<Member> findMembers() {
        return memberService.findAllMember();
    }
}
