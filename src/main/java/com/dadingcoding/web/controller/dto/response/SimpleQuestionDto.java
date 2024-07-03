package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QnA.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleQuestionDto {
    private Long questionId;
    private String question;

    public static SimpleQuestionDto toDto(Question question) {
        return SimpleQuestionDto.builder()
                .questionId(question.getId())
                .question(question.getContent())
                .build();
    }
}
