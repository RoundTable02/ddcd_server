package com.dadingcoding.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private final int status;
    private final String message;
}

@Getter
@Setter
@AllArgsConstructor
public class ExceptResponse {
    private final int status;
    private final String message;
    private final boolean success;
}
