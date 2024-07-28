package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MentorResponseDto {
    private Long mentor_id;
    private String name;
    private String email;
    private String phone;

    @Setter
    private String application;

    private Role role;

    @Setter
    private List<String> interview_schedule;

    @Setter
    private List<String> class_schedule;

    public static MentorResponseDto toDto(Member member) {
        return MentorResponseDto.builder()
                .mentor_id(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
    }
}
