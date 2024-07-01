package com.dadingcoding.web.response;

import lombok.Data;

@Data
public class ExceptResponse {
    private final int status;
    private final String message;
    private final boolean success;
}