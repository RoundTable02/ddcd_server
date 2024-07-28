package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class ScheduleResponseDto {
    private String menteeName;
    private String title;
    private String link;
    private String sessionNumber;
    private String message;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .menteeName(schedule.getMentee().getUsername())
                .title(schedule.getTitle())
                .link(schedule.getContent())
                .sessionNumber(schedule.getSessionNumber())
                .message(schedule.getContent())
                .build();
    }
}
