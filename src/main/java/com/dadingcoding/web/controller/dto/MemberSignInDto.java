package com.dadingcoding.web.controller.dto;

import com.dadingcoding.web.domain.Role;
import lombok.Getter;

@Getter
public class MemberSignInDto {
    private String email;
    private String password;
}
