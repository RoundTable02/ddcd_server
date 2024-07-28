package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddPostRequestDto;
import com.dadingcoding.web.controller.dto.request.AnswerRequestDto;
import com.dadingcoding.web.controller.dto.request.AvailableScheduleListReqeustDto;
import com.dadingcoding.web.controller.dto.request.UpdateReportRequestDto;
import com.dadingcoding.web.controller.dto.response.AnswerResponseDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.response.PostResponseDto;
import com.dadingcoding.web.controller.dto.response.ScheduleResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/mentor")
public class MentorController {
    private final MentorService mentorService;

    // 예비멘토 게시판 글 추가
    @PostMapping("/pre-mentor-board")
    public ResponseEntity<Response> addPreMentorBoardPost(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody AddPostRequestDto request) {
        mentorService.addPreMentorBoardPost(userAdaptor.getMember(), request);

        return ResponseEntity.status(200)
                .body(new Response(200, "게시글이 추가되었습니다."));
    }

    // 예비멘토 게시판 글 삭제
    @DeleteMapping("/pre-mentor-board/{post_id}")
    public ResponseEntity<Response> deletePreMentorBoardPost(@PathVariable long post_id) {
        mentorService.deletePreMentorBoardPost(post_id);

        return ResponseEntity.status(200)
                .body(new Response(200, "게시글이 삭제되었습니다."));
    }

    // 예비멘토 게시판 목록 조회
    @GetMapping("/pre-mentor-board")
    public ListResponseDto<PostResponseDto> getPreMentorBoardPosts() {
        List<PostResponseDto> preMentorBoardPosts = mentorService.getPreMentorBoardPosts();

        return new ListResponseDto<>(preMentorBoardPosts.size(), preMentorBoardPosts);
    }

    // 활동 보고서 작성 및 수정
    @PostMapping("/reports")
    public ResponseEntity<Response> addOrUpdateReport(
            @AuthenticationPrincipal UserAdaptor userAdaptor,
            @RequestBody UpdateReportRequestDto request
    ) {
        mentorService.addOrUpdateReport(userAdaptor.getMember(), request);

        return ResponseEntity.status(200)
                .body(new Response(200, "활동 보고서가 제출/수정되었습니다."));
    }

    // 수업 가능한 시간 기입
    @PostMapping("/available-times")
    public ResponseEntity<Response> addAvailableTimes(
            @AuthenticationPrincipal UserAdaptor userAdaptor,
            @RequestBody AvailableScheduleListReqeustDto availableScheduleListReqeustDto
            ) {
        mentorService.addAvailableTimes(userAdaptor.getMember(), availableScheduleListReqeustDto);

        return ResponseEntity.status(200)
                .body(new Response(200, "수업 가능 시간이 제출되었습니다."));
    }

    // 수업 일정 조회
    @GetMapping("/class-schedule")
    public ListResponseDto<ScheduleResponseDto> getClassSchedule(
            @AuthenticationPrincipal UserAdaptor userAdaptor
    ) {
        List<ScheduleResponseDto> classSchedule = mentorService.getClassSchedule(userAdaptor.getMember());

        return new ListResponseDto<>(classSchedule.size(), classSchedule);
    }


    // 멘티 질문 목록 조회
    @GetMapping("/mentees/questions")
    public ListResponseDto<AnswerResponseDto> getMenteeQuestions(
            @AuthenticationPrincipal UserAdaptor userAdaptor
    ) {
        List<AnswerResponseDto> menteeQuestions = mentorService.getMenteeQuestions(userAdaptor.getMember());

        return new ListResponseDto<>(menteeQuestions.size(), menteeQuestions);
    }

    // 멘티 질문 답변
    @PostMapping("/mentees/questions/{question_id}/answer")
    public ResponseEntity<Response> answerMenteeQuestion(@PathVariable Long question_id, @RequestBody AnswerRequestDto answerDto) {
        mentorService.answerMenteeQuestion(question_id, answerDto);

        return ResponseEntity.status(200)
                .body(new Response(200, "질문에 대한 답변이 제출되었습니다."));
    }
}
