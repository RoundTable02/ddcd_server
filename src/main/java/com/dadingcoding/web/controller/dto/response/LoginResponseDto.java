package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.security.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseDto {
    private JwtToken jwtToken;
    private SimpleMemberResponseDto user;
}
