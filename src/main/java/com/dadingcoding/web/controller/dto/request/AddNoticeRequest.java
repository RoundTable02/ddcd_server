package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddNoticeRequest {
    @Schema(description = "공지 제목", nullable = false, example = "2024 멘토를 모집합니다")
    private String title;

    @Schema(description = "공지 내용", nullable = false, example = "2024 멘토를 모집합니다 안녕하신지요?")
    private String content;

    public Notice toEntity(Member member) {
        return Notice.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
