package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.*;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.domain.*;
import com.dadingcoding.web.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MentorService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final AvailableScheduleRepository availableScheduleRepository;
    private final ReportRepository reportRepository;
    private final BoardPostRepository postRepository;
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponseDto> getClassSchedule(Member member) {
        List<Member> mentees = member.getMentees();

        List<ScheduleResponseDto> schedules = new ArrayList<>();

        for (Member mentee : mentees) {
            Schedule schedule = scheduleRepository.findByMenteeId(mentee.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당하는 멘티가 없습니다."));
            ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.toDto(schedule);
            schedules.add(scheduleResponseDto);
        }

        return schedules;
    }

    public List<AnswerResponseDto> getMenteeQuestions(Member member) {
        List<Member> mentees = member.getMentees();

        List<AnswerResponseDto> schedules = new ArrayList<>();

        for (Member mentee : mentees) {
            List<QuestionAnswer> questions = questionAnswerRepository.findAllByMemberId(mentee.getId());

            List<AnswerResponseDto> answerResponseDtos = questions.stream()
                    .map(AnswerResponseDto::toDto)
                    .collect(Collectors.toList());

            schedules.addAll(answerResponseDtos);
        }


        return schedules;
    }

    public void answerMenteeQuestion(Long questionId, AnswerRequestDto answerDto) {
        QuestionAnswer question = questionAnswerRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("해당하는 질문이 없습니다."));
        QuestionAnswer answer = new QuestionAnswer();
        answer.setQuestion(question);
        answer.setContent(answerDto.getAnswer());
        answer.setCreatedAt(LocalDateTime.now());
        questionAnswerRepository.save(answer);
    }
    public void addPreMentorBoardPost(Member member, AddPostRequestDto request) {
        // 예비멘토 게시판 글 추가 로직 구현
        BoardPost post = BoardPost.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();

        postRepository.save(post);
    }

    public void deletePreMentorBoardPost(long postId) {
        // 예비멘토 게시판 글 삭제 로직 구현
        postRepository.deleteById(postId);
    }

    public List<PostResponseDto> getPreMentorBoardPosts() {
        // 예비멘토 게시판 목록 조회 로직 구현
        List<BoardPost> posts = postRepository.findAll();

        return posts.stream()
                .map(PostResponseDto::toDto)
                .collect(Collectors.toList());
    }

    public void addOrUpdateReport(Member member, UpdateReportRequestDto request) {
        Report report = Report.builder()
                .member(member)
                .content(request.getContent())
                .build();

        reportRepository.save(report);
    }

    public void addAvailableTimes(Member member, AvailableScheduleListReqeustDto availableScheduleListReqeustDto) {
        List<AvailableScheduleReqeustDto> availableTimes = availableScheduleListReqeustDto.getAvailableTimes();
        for (AvailableScheduleReqeustDto availableTime : availableTimes) {
            AvailableSchedule availableSchedule = AvailableSchedule.builder()
                    .dayOfWeek(availableTime.getDayOfWeek())
                    .time(availableTime.getTime())
                    .build();
            availableSchedule.setMember(member);
            availableSchedule.setRole(member.getRole());

            availableScheduleRepository.save(availableSchedule);
        }
    }
}

