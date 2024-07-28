package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.ProgramStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class EditProgramRequestDto {
    /**
     * {
     *   "title": "string",
     *   "description": "string",
     *   "program_pic": "string",
     *   "mentors": ["string"],
     *   "start_date": "string",
     *   "end_date": "string",
     *   "status": "string", // "ongoing", "completed"
     *   "details": "string"
     * }
     */
    @Schema(description = "프로그램 제목", nullable = false, example = "2024 멘토링 참여하실분?")
    private String title;

    @Schema(description = "프로그램 설명", nullable = false, example = "8월~9월까지 진행하는 어쩌구 저쩌구")
    private String description;

    @Schema(description = "프로그램 사진 링크", nullable = false, example = "123abc.com")
    private String program_pic;

    @Schema(description = "참여하는 멘토들 id 리스트", nullable = false, example = "[1, 2, 3]")
    private List<Long> mentors;

    @Schema(description = "시작 시간", nullable = false, example = "2024-07-01T09:00:00.000000")
    private LocalDateTime start_date;

    @Schema(description = "종료 시간", nullable = false, example = "2024-07-08T09:00:00.000000")
    private LocalDateTime end_date;

    @Schema(description = "프로그램 상태 (TODO, RECRUIT, ONGOING, COMPLETED)", nullable = false, example = "COMPLETED")
    private ProgramStatus status;
    
    @Schema(description = "자세한 정보", nullable = false, example = "자세한 정보는 아래 링크 참조 어쩌구")
    private String details;
}
