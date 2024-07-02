package com.dadingcoding.web.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ListQuestionResponseDto<QuestionDto> {
    private List<QuestionDto> questions;

    @Builder
    public ListQuestionResponseDto(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
