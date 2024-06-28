package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddNoticeRequest {

    private String title;
    private String content;
    private String visibility;

    public Notice toEntity(Member member) {
        return Notice.builder()
                .title(title)
                .content(content)
                .visibility(visibility)
                .member(member)
                .build();
    }
}
