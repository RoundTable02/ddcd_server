package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptResponse {
    private int statusCode;
    private String message;
    private boolean success;
}
