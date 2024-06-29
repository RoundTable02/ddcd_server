package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Program;
import com.dadingcoding.web.domain.ProgramStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AddProgramRequestDto {
    /**
     * {
     *   "title": "string",
     *   "description": "string",
     *   "program_pic": "string",
     *   "tutors": ["string"],
     *   "start_date": "string",
     *   "end_date": "string",
     *   "status": "string", // "ongoing", "completed"
     *   "details": "string"
     * }
     */

    private String title;
    private String description;
    private String programPic;
    private List<Long> tutors;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProgramStatus status;
    private String details;

    public Program toEntity() {
        return Program.builder()
                .title(title)
                .description(description)
                .programPic(programPic)
                .startDate(startDate)
                .endDate(endDate)
                .status(status)
                .details(details)
                .build();

    }
}
