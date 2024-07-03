//package com.dadingcoding.web.service;
//
//import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
//import com.dadingcoding.web.response.InterviewScheduleResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public interface PrementorService {
//    void submitApplication(AddApplicationRequestDto request);
//
//    List<InterviewScheduleResponse> getInterviewSchedule();
//}
//
//@Service
//public class PrementorServiceImpl implements PrementorService {
//    private final ApplicationRepository applicationRepository;
//    private final MemberRepository memberRepository;
//
//    public PrementorServiceImpl(ApplicationRepository applicationRepository, MemberRepository memberRepository) {
//        this.applicationRepository = applicationRepository;
//        this.memberRepository = memberRepository;
//    }
//
//    @Override
//    public void submitApplication(AddApplicationRequestDto request) {
//        Member member = getLoggedInMember();
//        Application application = request.toEntity(member);
//        applicationRepository.save(application);
//    }
//
//    //수정 필요
//    @Override
//    public List<InterviewScheduleResponse> getInterviewSchedule() {
//        return List.of();
//    }
//
//    private Member getLoggedInMember() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        return memberRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//}
