package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportRequestDto {
    @Schema(description = "보고서 제목", nullable = false, example = "1회차 후기")
    private String title;

    @Schema(description = "보고서 내용", nullable = false, example = "참 재미있었다.")
    private String content;
}
