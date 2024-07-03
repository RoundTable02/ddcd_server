//package com.dadingcoding.web.service;
//
//import com.dadingcoding.web.domain.Member;
//import com.dadingcoding.web.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    public void updateProfile(Long memberId, UpdateProfileRequest request) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        member.setUsername(request.getUsername());
//        member.setEmail(request.getEmail());
//        member.setPhone(request.getPhone());
//
//        memberRepository.save(member);
//    }
//
//
//    @Transactional
//    public Member findMember(Long memberId) {
//       return memberRepository.findById(memberId).get();
//    }
//
//    @Transactional
//    public List<Member> findAllMember() {
//        return memberRepository.findAll();
//    }
//}
