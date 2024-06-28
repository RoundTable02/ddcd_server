package com.dadingcoding.web.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AdminScheduleRequestDto {
    private String scheduleType;
    private String schedule;
    private String title;
    private String content;
}
