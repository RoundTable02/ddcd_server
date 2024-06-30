package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AdminScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.domain.*;
import com.dadingcoding.web.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final MemberRepository memberRepository;
    private final ScheduleTimeRepository scheduleTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReportRepository reportRepository;
    private final ApplicationRepository applicationRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public List<TutorResponseDto> findAllMentors() {
        List<Member> tutors = memberRepository.findAllByRole(Role.MENTOR);

        List<TutorResponseDto> dtos = new ArrayList<>();

        for (Member tutor : tutors) {
            dtos.add(toTutorDto(tutor));
        }

        return dtos;
    }

    public TutorResponseDto findTutorById(Long tutorId) {
        Member tutor = memberRepository.findById(tutorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));
        return toTutorDto(tutor);
    }

    private TutorResponseDto toTutorDto(Member member) {
        TutorResponseDto dto = TutorResponseDto.toDto(member);

        List<Schedule> classSchedules = scheduleRepository.findAllByMentorIdAndScheduleType(member.getId(), ScheduleType.CLASS);
        List<Schedule> interviewSchedules = scheduleRepository.findAllByMenteeIdAndScheduleType(member.getId(), ScheduleType.INTERVIEW);

        Application application = findApplicationByMemberId(member.getId());

        dto.setClass_schedule(classSchedules);
        dto.setInterview_schedule(interviewSchedules);
        if (application != null)
            dto.setApplication(application.getContent());

        return dto;
    }

    private Application findApplicationByMemberId(Long memberId) {
        return applicationRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저의 지원서가 존재하지 않습니다."));
    }

    @Transactional
    public void changeTutorRole(Long tutorId, String role) {
        Member tutor = memberRepository.findById(tutorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        tutor.setRole(Role.valueOf(role));
    }

    @Transactional
    public void addSchedule(Member member, Long tutorId, AdminScheduleRequestDto adminScheduleRequestDto) {
        Member tutor = memberRepository.findById(tutorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

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

    public List<SimpleStudentResponseDto> findAllStudents() {
        return memberRepository.findAllByRole(Role.MENTEE).stream()
                .map(SimpleStudentResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(Long studentId) {
        Member student = memberRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("해당 학생이 존재하지 않습니다."));

        StudentResponseDto dto = StudentResponseDto.toDto(student);

        Application application = findApplicationByMemberId(studentId);
        dto.setApplication(application.getContent());

        List<QuestionAnswer> questions = questionAnswerRepository.findAllByMemberIdAndQnaType(studentId, QnaType.QUESTION);
        List<SimpleQuestionDto> questionDtos = questions.stream()
                .map(SimpleQuestionDto::toDto)
                .collect(Collectors.toList());

        dto.setQuestions(questionDtos);

        return dto;
    }

    public List<SimpleReportResponseDto> findAllReports() {
        List<Report> reportList = reportRepository.findAll();
        return reportList.stream()
                .map(SimpleReportResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public ReportDetailResponseDto findReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("보고서가 존재하지 않습니다."));
        return ReportDetailResponseDto.toDto(report);
    }
}
