package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QnA.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AnswerDto {
    private Long answer_id;
    private String answer;
    private LocalDateTime answered_at;

    public static AnswerDto toDto(Answer answer) {
        return AnswerDto.builder()
                .answer_id(answer.getId())
                .answer(answer.getContent())
                .answered_at(answer.getCreatedAt())
                .build();
    }

}
