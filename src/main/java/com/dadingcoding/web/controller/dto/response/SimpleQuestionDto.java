package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleQuestionDto {
    private Long questionId;
    private String question;

    public static SimpleQuestionDto toDto(QuestionAnswer questionAnswer) {
        return SimpleQuestionDto.builder()
                .questionId(questionAnswer.getId())
                .question(questionAnswer.getTitle())
                .build();
    }
}
