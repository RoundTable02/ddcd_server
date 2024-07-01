package com.dadingcoding.web.controller.dto.response;

import lombok.Data;

@Data
public class NoticeResponse {
    private final int status;
    private final String message;
    private final Object notice;

    public NoticeResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.notice = null;
    }

    public NoticeResponse(int status, Object notice) {
        this.status = status;
        this.message = "";
        this.notice = notice;
    }
}
