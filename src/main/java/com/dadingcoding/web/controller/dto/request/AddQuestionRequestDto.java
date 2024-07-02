package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QuestionAnswer;
import lombok.Data;

@Data
public class AddQuestionRequestDto {
    private String question;

    public QuestionAnswer toEntity(Member member) {
        return QuestionAnswer.builder()
                .content(question)
                .member(member)
                .build();
    }
}
