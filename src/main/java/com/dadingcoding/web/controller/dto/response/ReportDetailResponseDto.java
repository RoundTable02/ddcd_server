package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        Member mentor = report.getMember();
        List<Member> mentees = mentor.getMentees();
        List<SimpleReportMenteeDto> menteeDtos = mentees.stream()
                .map(SimpleReportMenteeDto::toDto)
                .collect(Collectors.toList());

        return ReportDetailResponseDto.builder()
                .reportId(report.getId())
                .tutorId(mentor.getId())
                .tutorName(mentor.getUsername())
                .content(report.getContent())
                .date(report.getCreatedAt())
                .mentees(menteeDtos)
                .build();
    }
}
