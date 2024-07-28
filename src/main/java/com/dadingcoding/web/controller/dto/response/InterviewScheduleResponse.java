package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScheduleResponse {
    private String time;
    private String title;
    private String message;

    public static InterviewScheduleResponse toDto(Schedule schedule) {
        return InterviewScheduleResponse.builder()
                .time(schedule.getScheduleTime().get(0)) // TODO : 변경 필요
                .title(schedule.getTitle())
                .message(schedule.getContent())
                .build();
    }
}
