package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class TutorResponseDto {
    private Long tutor_id;
    private String name;
    private String email;
    private String phone;

    @Setter
    private String application;

    private Role role;

    @Setter
    private List<Schedule> interview_schedule;

    @Setter
    private List<Schedule> class_schedule;

    public static TutorResponseDto toDto(Member member) {
        return TutorResponseDto.builder()
                .tutor_id(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
    }
}
