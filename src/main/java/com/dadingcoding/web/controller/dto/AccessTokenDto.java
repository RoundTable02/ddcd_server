package com.dadingcoding.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccessTokenDto {
    private final String username;
    private final String accessToken;
}
