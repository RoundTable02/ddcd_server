package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QnA.Question;
import com.dadingcoding.web.domain.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class QuestionDto {
    private Long question_id;
    private String question;
    private LocalDateTime created_at;

    public static QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .question_id(question.getId())
                .question(question.getContent())
                .created_at(question.getCreatedAt())
                .build();
    }
}
