package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.response.InterviewScheduleResponse;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.PrementorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예비 멘토 컨트롤러", description = "예비 멘토 관련 로직을 담당하는 컨트롤러입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/prementor")
public class PrementorController {
    private final PrementorService prementorService;

    // 지원서 작성 및 수정
    @Operation(summary = "지원서 작성 및 수정", description = "지원서 정보를 작성하거나 수정")
    @PostMapping("/application")
    public ResponseEntity<Response> submitApplication(
            @AuthenticationPrincipal UserAdaptor userAdaptor,
            @RequestBody AddApplicationRequestDto request
    ) {
        prementorService.submitApplication(userAdaptor.getMember(), request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "지원서가 성공적으로 제출되었습니다."));
    }

    // 면접 일정 조회
    @Operation(summary = "면접 스케줄 조회", description = "예비 멘토 자신의 면접 스케줄 조회")
    @GetMapping("/interview-schedule")
    public ListResponseDto<InterviewScheduleResponse> getInterviewSchedule(
            @AuthenticationPrincipal UserAdaptor userAdaptor
    ) {
        List<InterviewScheduleResponse> interviewSchedule = prementorService.getInterviewSchedule(userAdaptor.getMember());

        return new ListResponseDto(interviewSchedule.size(), interviewSchedule);
    }
}
