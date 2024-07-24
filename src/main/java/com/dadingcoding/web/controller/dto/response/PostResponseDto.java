package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.BoardPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public static PostResponseDto toDto(BoardPost boardPost) {
        return PostResponseDto.builder()
                .postId(boardPost.getId())
                .title(boardPost.getTitle())
                .content(boardPost.getContent())
                .author(boardPost.getMember().getUsername())
                .createdAt(boardPost.getCreatedAt())
                .build();
    }

}
