package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class ValidateEmailRequestDto {
    @Schema(description = "유효성 검사 대상 이메일", nullable = false, example = "example123@gmail.com")
    private String email;
}
