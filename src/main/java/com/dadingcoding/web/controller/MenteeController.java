package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.request.AddQuestionRequestDto;
import com.dadingcoding.web.controller.dto.response.AnswerDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.response.QuestionDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.exception.ErrorCode;
import com.dadingcoding.web.exception.NoAuthorityToAccessException;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mentee")
public class MenteeController {

    private final MenteeService menteeService;

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

    @GetMapping("questions")
    public ListResponseDto<QuestionDto> findAllQuestions (@AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();
        if (member.getRole() != Role.MENTEE) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        List<QuestionDto> questions = menteeService.findAllQuestions(member.getId());
        return new ListResponseDto<>(questions.size(), questions);
    }
//
//    @GetMapping("questions/{question_id}")
//    public ListResponseDto<QuestionDto> findAllQuestions (@PathVariable Long question_id,@AuthenticationPrincipal UserAdaptor userAdaptor) {
//        Member member = userAdaptor.getMember();
//        if (member.getRole() != Role.MENTEE) {
//            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
//        }
//
//
//
//        List<AnswerDto> answers = menteeService.findAllAnswers(question_id);
//        return new ListResponseDto<>(answers.size(), answers);
//    }



}
