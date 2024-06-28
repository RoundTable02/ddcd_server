package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Notice;
import lombok.Getter;

@Getter
public class NoticeResponse {
    private final String title;
    private final String content;
    private final String visibility;

    public NoticeResponse(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.visibility = notice.getVisibility();
    }
}
