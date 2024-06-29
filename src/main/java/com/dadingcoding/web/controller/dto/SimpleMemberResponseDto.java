package com.dadingcoding.web.controller.dto;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SimpleMemberResponseDto {
    private Long user_id;
    private String username;
    private String email;
    private Role role;

    public static SimpleMemberResponseDto toDto(Member member) {
        return SimpleMemberResponseDto.builder()
                .user_id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .role(member.getRole())
                .build();
    }
}
