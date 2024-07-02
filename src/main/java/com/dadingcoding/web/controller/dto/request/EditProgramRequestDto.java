package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.ProgramStatus;
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

    private String title;
    private String description;
    private String program_pic;
    private List<Long> mentors;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private ProgramStatus status;
    private String details;
}
