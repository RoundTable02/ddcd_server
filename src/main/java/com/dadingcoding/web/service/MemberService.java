package com.dadingcoding.web.service;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Member findMember(Long memberId) {
       return memberRepository.findById(memberId).get();
    }

    @Transactional
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }
}
