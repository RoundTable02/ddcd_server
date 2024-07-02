package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Program;
import com.dadingcoding.web.domain.ProgramStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class ProgramResponseDto {
    private Long program_id;
    private String title;
    private String description;
    private String details;
    private String program_pic;
    private List<SimpleMentorDto> mentors;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private ProgramStatus status;

    public static ProgramResponseDto toDto(Program program) {
        List<SimpleMentorDto> mentorDtoList = program.getProgramMembers().stream()
                .map(pm -> pm.getMember())
                .map(m -> SimpleMentorDto.toDto(m))
                .collect(Collectors.toList());

        return ProgramResponseDto.builder()
                .program_id(program.getId())
                .title(program.getTitle())
                .description(program.getDescription())
                .program_pic(program.getProgramPic())
                .details(program.getDetails())
                .mentors(mentorDtoList)
                .start_date(program.getStartDate())
                .end_date(program.getEndDate())
                .status(program.getStatus())
                .build();
    }
}
