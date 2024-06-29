package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccessTokenDto {
    private final String email;
    private final String accessToken;
}
