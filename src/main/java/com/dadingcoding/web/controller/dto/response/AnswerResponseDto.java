package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.QnA.Answer;
import com.dadingcoding.web.domain.QnA.Question;
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

    public static AnswerResponseDto toDto(Question question) {
        return AnswerResponseDto.builder()
                .questionId(question.getId())
                .studentName(question.getMember().getUsername())
                .question(question.getContent())
                .createdAt(question.getCreatedAt())
                .answer(null) // TODO : QNA 구조 변경 후 추가
                .build();
    }
}
