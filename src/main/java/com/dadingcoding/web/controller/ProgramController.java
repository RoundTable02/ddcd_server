package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddProgramRequestDto;
import com.dadingcoding.web.controller.dto.request.EditProgramRequestDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.response.ProgramResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "프로그램 컨트롤러", description = "프로그램을 관련 로직을 담당하는 컨트롤러입니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "프로그램 전체 조회", description = "DB에 담긴 모든 프로그램 정보 GET")
    @GetMapping
    public ListResponseDto<ProgramResponseDto> getPrograms() {
        List<ProgramResponseDto> programs = programService.getPrograms();

        return new ListResponseDto<>(programs.size(), programs);
    }

    @Operation(summary = "프로그램 개별 조회", description = "해당하는 프로그램의 정보 GET")
    @GetMapping("/{programId}")
    public ProgramResponseDto getProgram(@PathVariable Long programId) {
        return programService.getProgram(programId);
    }

    @Operation(summary = "프로그램 추가", description = "새로운 프로그램 추가")
    @PostMapping
    public ResponseEntity<Response> addProgram(@RequestBody AddProgramRequestDto requestDto) {
        programService.addProgram(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 등록되었습니다."));
    }

    @Operation(summary = "프로그램 수정", description = "등록한 프로그램 수정")
    @PutMapping("/{programId}")
    public ResponseEntity<Response> editProgram(@RequestBody EditProgramRequestDto requestDto, @PathVariable Long programId) {
        programService.editProgram(programId, requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 수정되었습니다."));
    }

    @Operation(summary = "프로그램 삭제", description = "등록한 프로그램 삭제")
    @DeleteMapping("/{programId}")
    public ResponseEntity<Response> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "프로그램이 삭제되었습니다."));
    }

}
