package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddProgramRequestDto;
import com.dadingcoding.web.controller.dto.request.EditProgramRequestDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.response.ProgramResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    @GetMapping
    public ListResponseDto<ProgramResponseDto> getPrograms() {
        List<ProgramResponseDto> programs = programService.getPrograms();

        return new ListResponseDto<>(programs.size(), programs);
    }

    @GetMapping("/{programId}")
    public ProgramResponseDto getProgram(@PathVariable Long programId) {
        return programService.getProgram(programId);
    }

    @PostMapping
    public ResponseEntity<Response> addProgram(@RequestBody AddProgramRequestDto requestDto) {
        programService.addProgram(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 등록되었습니다."));
    }

    @PutMapping("/{programId}")
    public ResponseEntity<Response> editProgram(@RequestBody EditProgramRequestDto requestDto, @PathVariable Long programId) {
        programService.editProgram(programId, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 수정되었습니다."));
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<Response> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 삭제되었습니다."));
    }

}
