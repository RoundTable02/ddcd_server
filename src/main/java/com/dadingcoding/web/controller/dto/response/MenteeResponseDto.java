package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MenteeResponseDto {
    /**
     * {
     *     "mentee_id": "string",
     *     "name": "string",
     *     "email": "string",
     *     "phone": "string",
     *     "application": "string",
     *     "questions": [
     *       {
     *         "question_id": "string",
     *         "question": "string"
     *       }
     *     ]
     *   }
     */
    private Long menteeId;
    private String name;
    private String email;
    private String phone;

    @Setter
    private String application;

    @Setter
    private List<SimpleQuestionDto> questions;

    public static MenteeResponseDto toDto(Member member) {
        return MenteeResponseDto.builder()
                .menteeId(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }

}
