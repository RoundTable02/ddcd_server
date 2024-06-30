package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class StudentResponseDto {
    /**
     * {
     *     "student_id": "string",
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
    private Long studentId;
    private String name;
    private String phone;
    private String email;

    @Setter
    private String application;

    @Setter
    private List<SimpleQuestionDto> questions;

    public static StudentResponseDto toDto(Member member) {
        return StudentResponseDto.builder()
                .studentId(member.getId())
                .name(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }

}
