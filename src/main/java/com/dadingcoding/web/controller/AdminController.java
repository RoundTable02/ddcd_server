package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AdminInterviewScheduleRequestDto;
import com.dadingcoding.web.controller.dto.request.AdminClassScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.controller.dto.request.MentorRoleRequestDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관리자 컨트롤러", description = "관리자만 접근 가능한 컨트롤러입니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "모든 멘토 검색", description = "DB에 저장된 모든 멘토 정보 GET")
    @GetMapping("/mentors")
    public ListResponseDto<MentorResponseDto> findAllMentors() {
        List<MentorResponseDto> allMentors = adminService.findAllMentors();
        return new ListResponseDto<>(allMentors.size(), allMentors);
    }

    @Operation(summary = "멘토 검색", description = "개별 멘토 정보 GET")
    @GetMapping("/mentors/{mentorId}")
    public MentorResponseDto findMentor(@PathVariable Long mentorId) {
        return adminService.findMentorById(mentorId);
    }

    @Operation(summary = "모든 멘티 검색", description = "DB에 저장된 모든 멘티 정보 GET")
    @GetMapping("/mentees")
    public ListResponseDto<SimpleMenteeResponseDto> findAllMentees() {
        List<SimpleMenteeResponseDto> mentees = adminService.findAllMentees();
        return new ListResponseDto<>(mentees.size(), mentees);
    }

    @Operation(summary = "멘티 검색", description = "개별 멘티 정보 GET")
    @GetMapping("/mentees/{menteeId}")
    public MenteeResponseDto findMentee(@PathVariable Long menteeId) {
        return adminService.findMenteeById(menteeId);
    }

    @Operation(summary = "멘토 Role 변경", description = "멘토 역할 변경 (MANAGER, PREMENTOR, MENTOR, MENTEE)")
    @PutMapping("/mentors/{mentorId}/role")
    public ResponseEntity<Response> changeMentorRole(@PathVariable Long mentorId, @RequestBody MentorRoleRequestDto mentorRoleRequestDto) {
        adminService.changeMentorRole(mentorId, mentorRoleRequestDto.getRole());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "멘토 권한이 변경되었습니다."));
    }

    @Operation(summary = "예비멘토 인터뷰 스케줄 추가", description = "예비멘토 인터뷰 LocalDateTime 형태로 추가")
    @PostMapping("/prementors/{prementorId}/schedule")
    public ResponseEntity<Response> addInterviewSchedule(@PathVariable Long prementorId, @RequestBody AdminInterviewScheduleRequestDto adminInterviewScheduleRequestDto) {
        adminService.addInterviewSchedule(prementorId, adminInterviewScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "일정이 업데이트되었습니다."));
    }

    @Operation(summary = "멘토 멘티 수업 스케줄 추가", description = "멘토 멘티 수업 스케줄 LocalDateTime 형태로 추가")
    @PostMapping("/mentors/{mentorId}/mentees/{menteeId}/schedule")
    public ResponseEntity<Response> addClassSchedule(
            @PathVariable Long mentorId, @PathVariable Long menteeId,
            @RequestBody AdminClassScheduleRequestDto adminClassScheduleRequestDto
    ) {
        adminService.addClassSchedule(mentorId, menteeId, adminClassScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "일정이 업데이트되었습니다."));
    }

    @Operation(summary = "모든 보고서 조회", description = "DB에 저장된 모든 보고서 정보 GET")
    @GetMapping("/reports")
    public ListResponseDto<SimpleReportResponseDto> findAllReports() {
        List<SimpleReportResponseDto> reports = adminService.findAllReports();
        return new ListResponseDto<>(reports.size(), reports);
    }

    @Operation(summary = "보고서 조회", description = "개별 보고서 정보 GET")
    @GetMapping("/reports/{reportId}")
    public ReportDetailResponseDto findReport(@PathVariable Long reportId) {
        return adminService.findReport(reportId);
    }

    @Operation(summary = "멘토 멘티 1대1 매칭 결과", description = "등록된 가능한 스케줄 기반으로 1대1 매칭한 결과 GET")
    @GetMapping("/matches")
    public ListResponseDto<MatchResponseDto> getMatches() {
        List<MatchResponseDto> matchDtos = adminService.getMatches();

        return new ListResponseDto<>(matchDtos.size(), matchDtos);
    }
}
