package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.ReportRequestDto;
import com.dadingcoding.web.controller.dto.response.ExceptResponse;
import com.dadingcoding.web.controller.dto.response.ReportResponseDto;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.repository.ReportRepository;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tutor/reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<Response> createReport(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        reportService.createReport(userAdaptor.getMember(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "보고서가 제출되었습니다."));
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<Response> updateReport(@PathVariable Long reportId, @AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        reportService.updateReport(reportId, userAdaptor.getMember(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "보고서가 수정되었습니다."));
    }
}
