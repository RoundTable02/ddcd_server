package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AdminInterviewScheduleRequestDto;
import com.dadingcoding.web.controller.dto.request.AdminClassScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.domain.*;
import com.dadingcoding.web.domain.QnA.Question;
import com.dadingcoding.web.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    private final InterviewRepository interviewRepository;
    private final QuestionRepository questionRepository;

    public List<MentorResponseDto> findAllMentors() {
        List<Member> mentors = memberRepository.findAllByRole(Role.MENTOR);

        List<MentorResponseDto> dtos = new ArrayList<>();

        for (Member mentor : mentors) {
            dtos.add(toMentorDto(mentor));
        }

        return dtos;
    }

    public MentorResponseDto findMentorById(Long mentorId) {
        Member mentor = memberRepository.findById(mentorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));
        return toMentorDto(mentor);
    }

    private MentorResponseDto toMentorDto(Member member) {
        MentorResponseDto dto = MentorResponseDto.toDto(member);

        List<Member> mentees = member.getMentees();
        List<String> classSchedules = new ArrayList<>();
        for (Member mentee : mentees) {
            List<Schedule> schedules = mentee.getSchedules();
            for (Schedule schedule : schedules) {
                classSchedules.addAll(schedule.getScheduleTime()); // 멘티의 스케줄은 멘토의 스케줄
            }
        }
        List<String> classSchedulesDistinct = classSchedules.stream().distinct().collect(Collectors.toList()); // 중복 제거

        List<Interview> interviewSchedules = interviewRepository.findAllByIntervieweeId(member.getId());
        List<String> interviewScheduleTimes = new ArrayList<>();
        if (!interviewSchedules.isEmpty()) {
            interviewSchedules.stream()
                            .forEach(interview -> interviewScheduleTimes.add(interview.getScheduleTime()));
        }

        Application application = null;
        try {
            application = findApplicationByMemberId(member.getId());
        } catch (NoSuchElementException e) {
            log.info("유저의 지원서가 존재하지 않습니다.");
        }

        dto.setClass_schedule(classSchedulesDistinct);
        dto.setInterview_schedule(interviewScheduleTimes);
        if (application != null)
            dto.setApplication(application.getContent());

        return dto;
    }

    private Application findApplicationByMemberId(Long memberId) {
        return applicationRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저의 지원서가 존재하지 않습니다."));
    }

    @Transactional
    public void changeMentorRole(Long mentorId, String role) {
        Member mentor = memberRepository.findById(mentorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        mentor.setRole(Role.valueOf(role));
    }

    @Transactional
    public void addInterviewSchedule(Long prementorId, AdminInterviewScheduleRequestDto adminInterviewScheduleRequestDto) {
        // 어드민 - 멘토 인터뷰 일정 추가

        Member prementor = memberRepository.findById(prementorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        LocalDateTime time = adminInterviewScheduleRequestDto.getTime();

        Schedule schedule = Schedule.builder()
                .mentee(prementor)
                .scheduleTime(List.of(time.toString()))
                .title(adminInterviewScheduleRequestDto.getTitle())
                .content(adminInterviewScheduleRequestDto.getTitle())
                .scheduleType(ScheduleType.INTERVIEW)
                .build();

        scheduleRepository.save(schedule);
    }

    @Transactional
    public void addClassSchedule(Long mentorId, Long menteeId, AdminClassScheduleRequestDto adminClassScheduleRequestDto) {
        // 멘토 - 멘티 인터뷰 일정 추가

        Member mentor = memberRepository.findById(mentorId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        Member mentee = memberRepository.findById(menteeId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        mentee.setMentor(mentor);

        LocalDateTime time = adminClassScheduleRequestDto.getTime();

        Schedule schedule = Schedule.builder()
                .mentee(mentee)
                .scheduleTime(List.of(time.toString()))
                .scheduleType(ScheduleType.CLASS)
                .sessionNumber(adminClassScheduleRequestDto.getSessionNumber())
                .title(adminClassScheduleRequestDto.getTitle())
                .content(adminClassScheduleRequestDto.getLink())
                .build();

        scheduleRepository.save(schedule);
    }

    public List<SimpleMenteeResponseDto> findAllMentees() {
        return memberRepository.findAllByRole(Role.MENTEE).stream()
                .map(SimpleMenteeResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public MenteeResponseDto findMenteeById(Long menteeId) {
        Member mentee = memberRepository.findById(menteeId)
                .orElseThrow(() -> new NoSuchElementException("해당 학생이 존재하지 않습니다."));

        MenteeResponseDto dto = MenteeResponseDto.toDto(mentee);

        Application application = null;
        try {
            application = findApplicationByMemberId(menteeId);
        } catch (NoSuchElementException e) {
            log.info("유저의 지원서가 존재하지 않습니다.");
        }
        if (application != null)
            dto.setApplication(application.getContent());

        List<Question> questions = questionRepository.findAllByMemberId(menteeId);
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


    /**
     * 멘토 - 멘티 매칭 알고리즘 (테스트 후 수정 필요)
     */
    static boolean[] visited; // 노드 방문 여부
    static int[] matchedMentee; // 각 멘토에게 매칭된 학생 정보
    static ArrayList<Integer>[] matchableList; // 연결 가능한 멘티 - 멘토 리스트

    public List<MatchResponseDto> getMatches() {
        List<Member> mentors = memberRepository.findAllByRole(Role.MENTOR);
        List<Member> mentees = memberRepository.findAllByRole(Role.MENTEE);

        int mentorSize = mentors.size();
        int menteeSize = mentees.size();

        // 간선 리스트
        matchableList = new ArrayList[menteeSize];
        for (int i = 0; i < menteeSize; i++) {
            matchableList[i] = new ArrayList<>();
        }

        // 멘토-멘티 간선 연결
        for (int i = 0; i < menteeSize; i++) {
            List<AvailableSchedule> menteeAvailableSchedules = mentees.get(i).getAvailableSchedules();
            for (int j = 0; j < mentorSize; j++) {
                List<AvailableSchedule> mentorAvailableSchedules = mentors.get(j).getAvailableSchedules();
                if (isMatchable(menteeAvailableSchedules, mentorAvailableSchedules)) {
                    matchableList[i].add(j);
                }
            }
        }

        matchedMentee = new int[menteeSize];
        for (int i = 0; i <= menteeSize; i++) {
            matchedMentee[i] = -1;
        }

        int result = 0;
        for (int i = 0; i <= menteeSize; i++) {
            visited = new boolean[menteeSize];
            if (dfs(i)) {
                result++;
            }
        }

        // dfs 결과 (matchedMentee)를 단순 리턴
        List<MatchResponseDto> responseDtoList = new ArrayList<>();

        for (int i = 0; i < matchedMentee.length; i++) {
            Member targetMentor = mentors.get(i);
            Member targetMentee = mentees.get(matchedMentee[i]);
            MemberMatchDto mentorDto = MemberMatchDto.toDto(targetMentor);
            MemberMatchDto menteeDto = MemberMatchDto.toDto(targetMentee);

            MatchResponseDto dto = MatchResponseDto.builder()
                    .mentor(mentorDto)
                    .mentee(menteeDto)
                    .build();

            responseDtoList.add(dto);
        }

        return responseDtoList;
    }

    //dfs
    public static boolean dfs(int node) {
        if (visited[node]) {
            return false;
        }
        visited[node] = true;

        for (int i = 0; i < matchableList[node].size(); i++) {
            int next = matchableList[node].get(i);
            if (matchedMentee[next] == -1 || dfs(matchedMentee[next])) {
                matchedMentee[next] = node;
                return true;
            }
        }
        return false;
    }


    // 멘티와 멘토가 매칭 가능한지 확인
    private boolean isMatchable(List<AvailableSchedule> menteeSchedules, List<AvailableSchedule> mentorSchedules) {
        menteeSchedules.retainAll(mentorSchedules);
        if (menteeSchedules.size() < 4) {
            // 최소 2시간 이상 스케줄이 겹쳐야 함
            return false;
        }

        // DayOfWeek & LocalTime 으로 오름차순
        menteeSchedules.sort(Comparator.comparingInt(AvailableSchedule::hashCode));

        // 연속된 2시간이 있는지 체크
        int check = 1;
        for (int i = 1; i < menteeSchedules.size(); i++) {
            if (check == 4) {
                return true;
            }
            AvailableSchedule before = menteeSchedules.get(i - 1);
            AvailableSchedule after = menteeSchedules.get(i);
            if (before.getDayOfWeek().equals(after.getDayOfWeek())) {
                if (after.getRank() - before.getRank() == 1) {
                    check += 1;
                }
                else {
                    check = 1;
                }
            }
            else {
                check = 1;
            }
        }

        return false;
    }
}
