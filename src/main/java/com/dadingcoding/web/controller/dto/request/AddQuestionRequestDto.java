package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QuestionAnswer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddQuestionRequestDto {

    @Schema(description = "질문 내용", nullable = false, example = "do-while 문은 언제 쓰나요?")
    private String question;

    public QuestionAnswer toEntity(Member member) {
        return QuestionAnswer.builder()
                .content(question)
                .member(member)
                .build();
    }
}
