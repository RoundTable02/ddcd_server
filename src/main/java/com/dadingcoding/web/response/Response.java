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
    private final Object notice;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.notice = null;
    }

    public Response(int status, Object notice) {
        this.status = status;
        this.message = "";
        this.notice = notice;
    }


}


