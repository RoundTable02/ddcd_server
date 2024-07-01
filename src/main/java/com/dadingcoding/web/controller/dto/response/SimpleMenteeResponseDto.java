package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SimpleMenteeResponseDto {
    private Long menteeId;
    private String name;
    private String email;
    private String phone;
    private String application;

    public static SimpleMenteeResponseDto toDto(Member member) {
        return SimpleMenteeResponseDto.builder()
                .menteeId(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
