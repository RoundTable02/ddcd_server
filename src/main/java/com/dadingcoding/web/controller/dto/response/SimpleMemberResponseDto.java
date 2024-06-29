package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SimpleMemberResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private String role;

    public static SimpleMemberResponseDto toDto(Member member) {
        return SimpleMemberResponseDto.builder()
                .userId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole().toString())
                .build();
    }
}
