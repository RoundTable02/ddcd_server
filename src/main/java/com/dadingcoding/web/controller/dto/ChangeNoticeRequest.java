package com.dadingcoding.web.controller.dto;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeNoticeRequest {

    private String title;
    private String content;
    private String visibility;
}
