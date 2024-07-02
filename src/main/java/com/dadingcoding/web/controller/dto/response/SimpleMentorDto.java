package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleMentorDto {
    private Long mentor_id;
    private String name;

    public static SimpleMentorDto toDto(Member member) {
        return SimpleMentorDto.builder()
                .mentor_id(member.getId())
                .name(member.getUsername())
                .build();
    }
}
