package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QnA.Question;
import lombok.Data;

@Data
public class AddQuestionRequestDto {

    private String question;

    public Question toEntity(Member member) {
        return Question.builder()
                .content(question)
                .member(member)
                .build();
    }
}
