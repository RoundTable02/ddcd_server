package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.response.InterviewScheduleResponse;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.PrementorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prementor")
public class PrementorController {
    private final PrementorService prementorService;

    // 지원서 작성 및 수정
    @PostMapping("/application")
    public ResponseEntity<?> submitApplication(@RequestBody AddApplicationRequestDto request) {
        try {
            prementorService.submitApplication(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "지원서가 성공적으로 제출되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }

    // 면접 일정 조회
    @GetMapping("/interview-schedule")
    public ResponseEntity<?> getInterviewSchedule() {
        try {
            List<InterviewScheduleResponse> schedules = prementorService.getInterviewSchedule();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(schedules);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "서버 내부 오류"));
        }
    }
}
