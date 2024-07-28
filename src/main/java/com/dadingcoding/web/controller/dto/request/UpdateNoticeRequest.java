package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateNoticeRequest {
    @Schema(description = "공지 제목", nullable = false, example = "2024 멘토를 모집합니다")
    private String title;

    @Schema(description = "공지 내용", nullable = false, example = "2024 멘토를 모집합니다 안녕하세요?")
    private String content;
}
