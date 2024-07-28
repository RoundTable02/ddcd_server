package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailableScheduleReqeustDto {
    @Schema(description = "가능한 요일 (영문 요일 FULLNAME)", nullable = false, example = "WEDNESDAY")
    private DayOfWeek dayOfWeek;

    @Schema(description = "가능한 시간", nullable = false, example = "09:00:00")
    private LocalTime time;
}
