package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {
    @Schema(description = "사용자 이름", nullable = false, example = "가나라")
    private String username;

    @Schema(description = "이메일", nullable = false, example = "example124@gmail.com")
    private String email;

    @Schema(description = "핸드폰 번호", nullable = false, example = "010-0000-0001")
    private String phone;
}
