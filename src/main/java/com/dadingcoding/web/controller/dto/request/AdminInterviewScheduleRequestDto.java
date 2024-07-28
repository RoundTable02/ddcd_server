package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class AdminInterviewScheduleRequestDto {
    @Schema(description = "인터뷰 시간", nullable = false, example = "2024-07-01T09:00:00.000000")
    private LocalDateTime time;

    @Schema(description = "인터뷰 제목", nullable = false, example = "7기 멘토 인터뷰")
    private String title;
}
