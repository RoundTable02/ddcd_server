package com.dadingcoding.web.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AdminScheduleRequestDto {
    private String schedule_type;
    private String schedule;
    private String title;
    private String content;
}
