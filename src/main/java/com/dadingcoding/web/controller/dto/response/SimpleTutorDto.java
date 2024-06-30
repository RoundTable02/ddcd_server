package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SimpleTutorDto {
    private Long tutor_id;
    private String name;

    public static SimpleTutorDto toDto(Member member) {
        return SimpleTutorDto.builder()
                .tutor_id(member.getId())
                .name(member.getUsername())
                .build();
    }
}
