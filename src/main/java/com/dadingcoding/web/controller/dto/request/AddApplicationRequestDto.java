package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import lombok.Data;

@Data
public class AddApplicationRequestDto {
    private String application;

    public Application toEntity(Member member) {
        return Application.builder()
                .content(application)
                .member(member)
                .build();
    }
}
