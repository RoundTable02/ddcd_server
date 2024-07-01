package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AdminScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.domain.*;
import com.dadingcoding.web.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

        List<Member> mentees = member.getMentees();
        List<Schedule> classSchedules = new ArrayList<>();
        for (Member mentee : mentees) {
            List<Schedule> menteeClassSchedule = mentee.getSchedules().stream()
                    .filter(s -> s.getScheduleType().equals(ScheduleType.CLASS))
                    .collect(Collectors.toList());
            classSchedules.addAll(menteeClassSchedule); // 멘티의 스케줄은 멘토의 스케줄
        }
        List<Schedule> classSchedulesDistinct = classSchedules.stream().distinct().collect(Collectors.toList()); // 중복 제거

        List<Schedule> interviewSchedules = member.getSchedules(); // 멘토 자신이 멘티인 경우는 인터뷰

        Application application = null;
        try {
            application = findApplicationByMemberId(member.getId());
        } catch (NoSuchElementException e) {
            log.info("유저의 지원서가 존재하지 않습니다.");
        }

        dto.setClass_schedule(classSchedulesDistinct);
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

        LocalDateTime time = adminScheduleRequestDto.getSchedule().getStart_time();

        Schedule schedule = Schedule.builder()
                .mentee(tutor)
                .scheduleTime(List.of(time.toString()))
                .scheduleType(ScheduleType.valueOf(adminScheduleRequestDto.getSchedule_type()))
                .build(); // 어드민 - 멘토 인터뷰 일정 추가
        // TODO : 멘티 ID 추가시 수정 예정

        schedule.setTitle(adminScheduleRequestDto.getSchedule().getContent());
        schedule.setContent(adminScheduleRequestDto.getSchedule().getContent());

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

        Application application = null;
        try {
            application = findApplicationByMemberId(studentId);
        } catch (NoSuchElementException e) {
            log.info("유저의 지원서가 존재하지 않습니다.");
        }
        if (application != null)
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
