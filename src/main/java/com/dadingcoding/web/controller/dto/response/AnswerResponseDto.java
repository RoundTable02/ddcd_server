package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AnswerResponseDto {
    private Long questionId;
    private String studentName;
    private String question;
    private LocalDateTime createdAt;
    private String answer;

    public static AnswerResponseDto toDto(QuestionAnswer answer) {
        return AnswerResponseDto.builder()
                .questionId(answer.getId())
                .studentName(answer.getMember().getUsername())
                .question(answer.getQuestion().getContent())
                .createdAt(answer.getCreatedAt())
                .answer(answer.getContent())
                .build();
    }
}
