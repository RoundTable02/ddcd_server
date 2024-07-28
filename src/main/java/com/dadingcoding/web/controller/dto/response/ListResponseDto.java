package com.dadingcoding.web.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ListResponseDto <T> {
    private int size;
    private List<T> data;

    @Builder
    public ListResponseDto(int size, List<T> data) {
        this.size = size;
        this.data = data;
    }
}
