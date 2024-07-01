package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class SimpleReportResponseDto {
    private Long reportId;
    private Long tutorId;
    private LocalDateTime date;

    public static SimpleReportResponseDto toDto(Report report) {
        return SimpleReportResponseDto.builder()
                .reportId(report.getId())
                .tutorId(report.getMember().getId())
                .date(report.getCreatedAt())
                .build();
    }
}
