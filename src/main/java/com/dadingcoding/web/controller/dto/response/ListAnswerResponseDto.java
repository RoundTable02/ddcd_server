package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QuestionAnswer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ListAnswerResponseDto<AnswerDto> {
    private Long question_id;
    private String student_name;
    private String question;
    private LocalDateTime created_at;
    private List<AnswerDto> answer;

    @Builder
    public ListAnswerResponseDto(QuestionAnswer question, List<AnswerDto> answer) {
        this.question_id = question.getId();
        this.student_name = question.getMember().getUsername();
        this.question = question.getContent();
        this.created_at = question.getCreatedAt();
        this.answer = answer;
    }
}
