package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginDto {
    @Schema(description = "이메일", nullable = false, example = "example123@gmail.com")
    private String email;

    @Schema(description = "비밀번호", nullable = false, example = "qwe123")
    private String password;
}
