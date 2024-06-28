package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddProgramRequestDto;
import com.dadingcoding.web.controller.dto.request.EditProgramRequestDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;


    @PostMapping
    public void addProgram(@RequestBody AddProgramRequestDto requestDto) {
        programService.addProgram(requestDto);

        //TODO: Response Value Required
    }

    @PutMapping("/{programId}")
    public void editProgram(@RequestBody EditProgramRequestDto requestDto, @PathVariable Long programId) {
        programService.editProgram(programId, requestDto);

        //TODO: Response Value Required
    }

    @DeleteMapping("/{programId}")
    public void deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);

        //TODO: Response Value Required
    }

}
