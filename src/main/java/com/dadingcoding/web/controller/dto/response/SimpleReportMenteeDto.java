package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleReportMenteeDto {
    private Long menteeId;
    private String name;

    public static SimpleReportMenteeDto toDto(Member member) {
        return SimpleReportMenteeDto.builder()
                .menteeId(member.getId())
                .name(member.getUsername())
                .build();
    }
}
