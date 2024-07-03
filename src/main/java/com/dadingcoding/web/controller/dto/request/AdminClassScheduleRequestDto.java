package com.dadingcoding.web.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class AdminClassScheduleRequestDto {
    private LocalDateTime time;
    private String title;
    private String link;
    private String sessionNumber;
}
