package com.dadingcoding.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPostRequestDto {
    @Schema(description = "게시글 제목", nullable = false, example = "안녕하세요 멘토 ~입니다")
    private String title;

    @Schema(description = "게시글 내용", nullable = false, example = "안녕하신지요?")
    private String content;
}
