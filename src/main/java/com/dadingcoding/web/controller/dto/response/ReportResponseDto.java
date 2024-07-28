package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public static ReportResponseDto toDto(Report report) {
        return ReportResponseDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .content(report.getContent())
                .createdDate(report.getCreatedAt())
                .build();
    }
}
