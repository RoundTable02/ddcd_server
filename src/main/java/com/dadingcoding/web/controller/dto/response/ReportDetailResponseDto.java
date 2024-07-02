package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class ReportDetailResponseDto {
    /**
     * {
     *   "report_id": "1",
     *   "mentor_id": "3",
     *   "mentor_name": "김채영",
     *   "content": "이번 차시는 반복문을 다뤘음.....",
     *   "date": "2024-06-15",
     *   "mentees": [
     *     {
     *       "mentee_id": "10",
     *       "name": "김ㅇㅇ"
     *     }
     *   ]
     *   }
     */

    private Long reportId;
    private Long mentorId;
    private String mentorName;
    private String content;
    private LocalDateTime date;
    private List<SimpleReportMenteeDto> mentees;

    public static ReportDetailResponseDto toDto(Report report) {
        Schedule schedule = report.getSchedule();
        Member mentee = schedule.getMentee();
        SimpleReportMenteeDto menteeDto = SimpleReportMenteeDto.toDto(mentee);

        return ReportDetailResponseDto.builder()
                .reportId(report.getId())
                .mentorId(schedule.getMentee().getId())
                .mentorName(report.getMember().getUsername())
                .content(report.getContent())
                .date(report.getCreatedAt())
                .mentees(List.of(menteeDto))
                .build();
    }
}
