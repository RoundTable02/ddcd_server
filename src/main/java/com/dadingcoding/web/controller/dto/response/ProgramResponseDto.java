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
    private String program_pic;
    private List<SimpleTutorDto> tutors;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private ProgramStatus status;

    public static ProgramResponseDto toDto(Program program) {
        List<SimpleTutorDto> tutorDtoList = program.getProgramMembers().stream()
                .map(pm -> pm.getMember())
                .map(m -> SimpleTutorDto.toDto(m))
                .collect(Collectors.toList());

        return ProgramResponseDto.builder()
                .program_id(program.getId())
                .title(program.getTitle())
                .description(program.getDescription())
                .program_pic(program.getProgramPic())
                .tutors(tutorDtoList)
                .start_date(program.getStartDate())
                .end_date(program.getEndDate())
                .status(program.getStatus())
                .build();
    }
}
