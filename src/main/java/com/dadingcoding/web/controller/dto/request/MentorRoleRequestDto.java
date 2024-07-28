package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@AllArgsConstructor
public class MentorRoleRequestDto {
    @Schema(description = "바뀔 역할 (MANAGER, PREMENTOR, MENTOR, MENTEE)", nullable = false, example = "MANAGER")
    private String role;
}
