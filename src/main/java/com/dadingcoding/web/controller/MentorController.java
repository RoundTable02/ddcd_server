package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.*;
import com.dadingcoding.web.controller.dto.response.AnswerResponseDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.response.PostResponseDto;
import com.dadingcoding.web.controller.dto.response.ScheduleResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "멘토 컨트롤러", description = "멘토 관련 로직을 담당하는 컨트롤러입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mentor")
public class MentorController {
    private final MentorService mentorService;

    // 예비멘토 게시판 글 추가
    @Operation(summary = "예비 멘토 게시판 글 작성", description = "예비 멘토 게시판 글 작성")
    @PostMapping("/pre-mentor-board")
    public ResponseEntity<Response> addPreMentorBoardPost(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody AddPostRequestDto request) {
        mentorService.addPreMentorBoardPost(userAdaptor.getMember(), request);

        return ResponseEntity.status(200)
                .body(new Response(200, "게시글이 추가되었습니다."));
    }

    // 예비멘토 게시판 글 삭제
    @Operation(summary = "예비 멘토 게시판 글 삭제", description = "예비 멘토 게시판 글 삭제")
    @DeleteMapping("/pre-mentor-board/{post_id}")
    public ResponseEntity<Response> deletePreMentorBoardPost(@PathVariable long post_id) {
        mentorService.deletePreMentorBoardPost(post_id);

        return ResponseEntity.status(200)
                .body(new Response(200, "게시글이 삭제되었습니다."));
    }

    // 예비멘토 게시판 목록 조회
    @Operation(summary = "예비 멘토 게시판 글 목록 조회", description = "예비 멘토 게시판 글 목록 조회")
    @GetMapping("/pre-mentor-board")
    public ListResponseDto<PostResponseDto> getPreMentorBoardPosts() {
        List<PostResponseDto> preMentorBoardPosts = mentorService.getPreMentorBoardPosts();

        return new ListResponseDto<>(preMentorBoardPosts.size(), preMentorBoardPosts);
    }

    // 활동 보고서 작성 및 수정
    @Operation(summary = "활동 보고서 작성", description = "활동 보고서 작성")
    @PostMapping("/reports")
    public ResponseEntity<Response> createReport(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        mentorService.createReport(userAdaptor.getMember(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "보고서가 제출되었습니다."));
    }

    @Operation(summary = "활동 보고서 수정", description = "활동 보고서 수정")
    @PutMapping("/reports/{reportId}")
    public ResponseEntity<Response> updateReport(@PathVariable Long reportId, @AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        mentorService.updateReport(reportId, userAdaptor.getMember(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "보고서가 수정되었습니다."));
    }

    // 수업 가능한 시간 기입
    @Operation(summary = "멘토 수업 가능한 시간 추가", description = "멘토의 수업 가능한 시간을 리스트 형태로 추가")
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
    @Operation(summary = "수업 일정 확인", description = "확정된 수업 일정을 확인")
    @GetMapping("/class-schedule")
    public ListResponseDto<ScheduleResponseDto> getClassSchedule(
            @AuthenticationPrincipal UserAdaptor userAdaptor
    ) {
        List<ScheduleResponseDto> classSchedule = mentorService.getClassSchedule(userAdaptor.getMember());

        return new ListResponseDto<>(classSchedule.size(), classSchedule);
    }


    // 멘티 질문 목록 조회
    @Operation(summary = "멘티 질문 조회", description = "자신의 멘티가 추가한 모든 질문 확인")
    @GetMapping("/mentees/questions")
    public ListResponseDto<AnswerResponseDto> getMenteeQuestions(
            @AuthenticationPrincipal UserAdaptor userAdaptor
    ) {
        List<AnswerResponseDto> menteeQuestions = mentorService.getMenteeQuestions(userAdaptor.getMember());

        return new ListResponseDto<>(menteeQuestions.size(), menteeQuestions);
    }

    // 멘티 질문 답변
    @Operation(summary = "멘티 질문 답변", description = "질문에 대한 답변 추가")
    @PostMapping("/mentees/questions/{question_id}/answer")
    public ResponseEntity<Response> answerMenteeQuestion(@PathVariable Long question_id, @RequestBody AnswerRequestDto answerDto) {
        mentorService.answerMenteeQuestion(question_id, answerDto);

        return ResponseEntity.status(200)
                .body(new Response(200, "질문에 대한 답변이 제출되었습니다."));
    }
}
