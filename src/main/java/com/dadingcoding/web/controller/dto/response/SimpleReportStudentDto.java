package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleReportStudentDto {
    private Long studentId;
    private String name;

    public static SimpleReportStudentDto toDto(Member member) {
        return SimpleReportStudentDto.builder()
                .studentId(member.getId())
                .name(member.getUsername())
                .build();
    }
}
