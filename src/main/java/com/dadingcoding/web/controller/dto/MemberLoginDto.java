package com.dadingcoding.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginDto {
    private String email;
    private String password;
}
