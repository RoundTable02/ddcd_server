package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class AdminClassScheduleRequestDto {
    @Schema(description = "수업 확정 시간", nullable = false, example = "2024-07-01T09:00:00.000000")
    private LocalDateTime time;

    @Schema(description = "수업 제목", nullable = false, example = "7기 멘토링 프로그램")
    private String title;

    @Schema(description = "수업 링크", nullable = false, example = "zoom123.com")
    private String link;

    @Schema(description = "수업 회차", nullable = false, example = "1회")
    private String sessionNumber;
}
