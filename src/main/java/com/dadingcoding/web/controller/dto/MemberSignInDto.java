package com.dadingcoding.web.controller.dto;

import com.dadingcoding.web.domain.Role;
import lombok.Getter;

@Getter
public class MemberSignInDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
