package com.dadingcoding.web.dto;

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
    private Member member;

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .visibility(visibility)
                .build();
    }
}
