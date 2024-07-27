package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Schedule;
import com.dadingcoding.web.domain.ScheduleType;
import com.dadingcoding.web.repository.ApplicationRepository;
import com.dadingcoding.web.repository.ScheduleRepository;
import com.dadingcoding.web.response.InterviewScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PrementorService {
    private final ApplicationRepository applicationRepository;
    private final ScheduleRepository scheduleRepository;

    public void submitApplication(Member member, AddApplicationRequestDto request) {
        Application application = request.toEntity(member);
        applicationRepository.save(application);
    }

    public List<InterviewScheduleResponse> getInterviewSchedule(Member member) {
        List<Schedule> interviews = scheduleRepository.findByMenteeIdAndScheduleType(member.getId(), ScheduleType.INTERVIEW);
        return interviews.stream()
                .map(InterviewScheduleResponse::toDto)
                .collect(Collectors.toList());
    }
}
