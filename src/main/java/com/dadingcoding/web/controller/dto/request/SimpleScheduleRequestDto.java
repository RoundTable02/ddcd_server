package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @NoArgsConstructor
@AllArgsConstructor
public class SimpleScheduleRequestDto {
    private String content;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
}
