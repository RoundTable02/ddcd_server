package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AdminInterviewScheduleRequestDto;
import com.dadingcoding.web.controller.dto.request.AdminClassScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.controller.dto.request.MentorRoleRequestDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/mentors")
    public ListResponseDto<MentorResponseDto> findAllMentors() {
        List<MentorResponseDto> allMentors = adminService.findAllMentors();
        return new ListResponseDto<>(allMentors.size(), allMentors);
    }

    @GetMapping("/mentors/{mentorId}")
    public MentorResponseDto findMentor(@PathVariable Long mentorId) {
        return adminService.findMentorById(mentorId);
    }

    @GetMapping("/mentees")
    public ListResponseDto<SimpleMenteeResponseDto> findAllMentees() {
        List<SimpleMenteeResponseDto> mentees = adminService.findAllMentees();
        return new ListResponseDto<>(mentees.size(), mentees);
    }

    @GetMapping("/mentees/{menteeId}")
    public MenteeResponseDto findMentee(@PathVariable Long menteeId) {
        return adminService.findMenteeById(menteeId);
    }

    @PutMapping("/mentors/{mentorId}/role")
    public ResponseEntity<Response> changeMentorRole(@PathVariable Long mentorId, @RequestBody MentorRoleRequestDto mentorRoleRequestDto) {
        adminService.changeMentorRole(mentorId, mentorRoleRequestDto.getRole());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "멘토 권한이 변경되었습니다."));
    }

    @PostMapping("/prementors/{prementorId}/schedule")
    public ResponseEntity<Response> addInterviewSchedule(@PathVariable Long prementorId, @RequestBody AdminInterviewScheduleRequestDto adminInterviewScheduleRequestDto) {
        adminService.addInterviewSchedule(prementorId, adminInterviewScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "일정이 업데이트되었습니다."));
    }

    @PostMapping("/mentors/{mentorId}/mentees/{menteeId}/schedule")
    public ResponseEntity<Response> addClassSchedule(@PathVariable Long mentorId, @PathVariable Long menteeId, @RequestBody AdminClassScheduleRequestDto adminClassScheduleRequestDto) {
        adminService.addClassSchedule(mentorId, menteeId, adminClassScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "일정이 업데이트되었습니다."));
    }

    @GetMapping("/reports")
    public ListResponseDto<SimpleReportResponseDto> findAllReports() {
        List<SimpleReportResponseDto> reports = adminService.findAllReports();
        return new ListResponseDto<>(reports.size(), reports);
    }

    @GetMapping("/reports/{reportId}")
    public ReportDetailResponseDto findReport(@PathVariable Long reportId) {
        return adminService.findReport(reportId);
    }
}
