package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.UpdateProfileRequest;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void updateProfile(Member member, UpdateProfileRequest request) {
        member.updateProfile(request.getUsername(), request.getEmail(), request.getPhone());

        memberRepository.save(member);
    }
}
