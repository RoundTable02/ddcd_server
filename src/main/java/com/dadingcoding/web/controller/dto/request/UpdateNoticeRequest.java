package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateNoticeRequest {
    private String title;
    private String content;
    private String visibility;
}
