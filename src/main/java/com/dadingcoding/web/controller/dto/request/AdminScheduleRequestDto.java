package com.dadingcoding.web.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AdminScheduleRequestDto {
    private String schedule_type;
    private SimpleScheduleRequestDto schedule;
}
