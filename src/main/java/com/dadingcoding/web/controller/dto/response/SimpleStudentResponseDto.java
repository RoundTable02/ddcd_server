package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SimpleStudentResponseDto {
    private Long studentId;
    private String name;
    private String email;
    private String phone;
    private String application;

    public static SimpleStudentResponseDto toDto(Member member) {
        return SimpleStudentResponseDto.builder()
                .studentId(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
