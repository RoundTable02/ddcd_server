package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetProfileResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private Role role;

    public static GetProfileResponseDto toDto(Member member) {
        return GetProfileResponseDto.builder()
                .userId(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
    }
}
