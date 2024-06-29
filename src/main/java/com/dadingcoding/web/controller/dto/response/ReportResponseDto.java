package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public static ReportResponseDto fromEntity(Report report) {
        return new ReportResponseDto(
                report.getId(),
                report.getTitle(),
                report.getContent(),
                report.getCreateDate()
        );
    }
}
