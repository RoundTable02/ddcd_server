package com.dadingcoding.web.controller;

import com.dadingcoding.web.domain.Mentor;
import com.dadingcoding.web.request.AddPostRequest;
import com.dadingcoding.web.request.UpdateReportRequest;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addPreMentorBoardPost(@RequestBody AddPostRequest request) {
        try {
            mentorService.addPreMentorBoardPost(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(201, "게시글이 성공적으로 추가되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 예비멘토 게시판 글 삭제
    @DeleteMapping("/pre-mentor-board/{post_id}")
    public ResponseEntity<?> deletePreMentorBoardPost(@PathVariable long post_id) {
        try {
            mentorService.deletePreMentorBoardPost(post_id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "게시글이 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 예비멘토 게시판 목록 조회
    @GetMapping("/pre-mentor-board")
    public ResponseEntity<?> getPreMentorBoardPosts() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mentorService.getPreMentorBoardPosts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 활동 보고서 작성 및 수정
    @PostMapping("/reports")
    public ResponseEntity<?> addOrUpdateReport(@RequestBody UpdateReportRequest request) {
        try {
            mentorService.addOrUpdateReport(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "보고서가 성공적으로 작성/수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 수업 가능한 시간 기입
    @PostMapping("/available-times")
    public ResponseEntity<?> addAvailableTimes(@RequestBody List<String> times) {
        try {
            mentorService.addAvailableTimes(times);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "수업 가능한 시간이 성공적으로 추가되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 수업 일정 조회
    @GetMapping("/class-schedule")
    public ResponseEntity<?> getClassSchedule() {
        try {
            List<ClassScheduleResponse> classSchedule = mentorService.getClassSchedule();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(classSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }


    // 멘티 질문 목록 조회
    @GetMapping("/mentees/questions")
    public ResponseEntity<?> getMenteeQuestions() {
        try {
            List<ListAnswerResponseDto> questions = mentorService.getMenteeQuestions();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mentorService.getMenteeQuestions());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 멘티 질문 답변
    @PostMapping("/mentees/questions/{question_id}/answer")
    public ResponseEntity<?> answerMenteeQuestion(@PathVariable Long question_id, @RequestBody AnswerDto answerDto) {
        try {
            mentorService.answerMenteeQuestion(question_id, answerDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "질문에 대한 답변이 제출되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }
}
