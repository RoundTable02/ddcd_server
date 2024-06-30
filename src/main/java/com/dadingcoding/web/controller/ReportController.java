package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.ReportRequestDto;
import com.dadingcoding.web.controller.dto.response.ExceptResponse;
import com.dadingcoding.web.controller.dto.response.ReportResponseDto;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.repository.ReportRepository;
import com.dadingcoding.web.security.UserAdaptor;
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
    private final ReportRepository reportRepository;

    @PostMapping
    public ResponseEntity<?> createReport(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        try {
            Report report = new Report();
            report.setMember(userAdaptor.getMember());
            report.setTitle(requestDto.getTitle());
            report.setContent(requestDto.getContent());
            report = reportRepository.save(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(ReportResponseDto.fromEntity(report));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<?> updateReport(@PathVariable Long reportId, @AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ReportRequestDto requestDto) {
        try {
            Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
            if (!report.getMember().getId().equals(userAdaptor.getMember().getId())) {
                throw new RuntimeException("이 보고서를 수정할 권한이 없습니다");
            }
            report.setTitle(requestDto.getTitle());
            report.setContent(requestDto.getContent());
            report = reportRepository.save(report);
            return ResponseEntity.status(HttpStatus.OK).body(ReportResponseDto.fromEntity(report));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }
}
