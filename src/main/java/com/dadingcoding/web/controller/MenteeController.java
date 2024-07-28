package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.request.AddQuestionRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QuestionAnswer;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.exception.ErrorCode;
import com.dadingcoding.web.exception.NoAuthorityToAccessException;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MenteeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "멘티 컨트롤러", description = "멘티 관련 로직을 담당하는 컨트롤러입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mentee")
public class MenteeController {

    private final MenteeService menteeService;

    @Operation(summary = "멘티 지원서 작성", description = "멘티 지원서 작성")
    @PostMapping("application")
    public ResponseEntity<Response> addApplication(@RequestBody AddApplicationRequestDto request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        if (member.getRole() != Role.MENTEE) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        menteeService.addApplication(member, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "지원서가 제출되었습니다."));
    }

    @Operation(summary = "멘티 질문 추가", description = "멘티 질문 추가")
    @PostMapping("questions")
    public ResponseEntity<Response> addQuestion(@RequestBody AddQuestionRequestDto request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        if (member.getRole() != Role.MENTEE) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        menteeService.addQuestion(member, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "질문이 제출되었습니다."));
    }

    @Operation(summary = "자신의 모든 질문 조회", description = "자신이 작성한 모든 질문 GET")
    @GetMapping("questions")
    public ListQuestionResponseDto<QuestionDto> findAllQuestions (@AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        if (member.getRole() != Role.MENTEE) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        List<QuestionDto> questions = menteeService.findAllQuestions(member.getId());
        return new ListQuestionResponseDto<>(questions);
    }

    @Operation(summary = "자신의 질문 조회", description = "자신이 작성한 개별 질문 GET")
    @GetMapping("questions/{question_id}")
    public ListAnswerResponseDto<AnswerDto> findAllQuestions (@PathVariable Long question_id, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        if (member.getRole() != Role.MENTEE) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        List<AnswerDto> answers = menteeService.findAllAnswers(question_id);
        QuestionAnswer question = menteeService.findQuestionById(question_id);
        return new ListAnswerResponseDto<>(question, answers);
    }



}
