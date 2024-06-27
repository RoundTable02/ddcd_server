package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.AdminScheduleRequestDto;
import com.dadingcoding.web.controller.dto.ChangeNoticeRequest;
import com.dadingcoding.web.domain.*;
import com.dadingcoding.web.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final MemberRepository memberRepository;
    private final ScheduleTimeRepository scheduleTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReportRepository reportRepository;
    private final NoticeRepository noticeRepository;

    public List<Member> findAllMentors() {
        return memberRepository.findAllByRole(Role.MENTOR);
    }


    public Member findTutorById(Long tutorId) {
        return memberRepository.findById(tutorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));
    }

    @Transactional
    public void changeTutorRole(Long tutorId, String role) {
        Member tutor = findTutorById(tutorId);
        tutor.setRole(Role.valueOf(role));
    }

    @Transactional
    public void addSchedule(Member member, Long tutorId, AdminScheduleRequestDto adminScheduleRequestDto) {
        Member tutor = findTutorById(tutorId);
        LocalTime time = LocalTime.parse(adminScheduleRequestDto.getSchedule());
        ScheduleTime scheduleTime = scheduleTimeRepository.findByAvailableTime(time)
                .orElseThrow(() -> new NoSuchElementException("스케줄이 존재하지 않습니다."));

        Schedule schedule = Schedule.builder()
                .mentor(member)
                .mentee(tutor)
                .scheduleTimeList(List.of(scheduleTime))
                .title(adminScheduleRequestDto.getTitle())
                .content(adminScheduleRequestDto.getContent())
                .scheduleType(ScheduleType.valueOf(adminScheduleRequestDto.getScheduleType()))
                .build();

        scheduleRepository.save(schedule);
    }

    public List<Member> findAllStudents() {
        return memberRepository.findAllByRole(Role.MENTEE);
    }

    public Member findStudentById(Long studentId) {
        return memberRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("해당 학생이 존재하지 않습니다."));
    }

    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }
}
