package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddApplicationRequestDto {
    @Schema(description = "지원서 내용", nullable = false, example = "안녕하세요 저는...")
    private String application;

    public Application toEntity(Member member) {
        return Application.builder()
                .content(application)
                .member(member)
                .build();
    }
}
