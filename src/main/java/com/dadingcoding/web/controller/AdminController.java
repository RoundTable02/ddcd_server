package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AdminScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.*;
import com.dadingcoding.web.controller.dto.request.TutorRoleRequestDto;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/tutors")
    public ListResponseDto<TutorResponseDto> findAllTutors() {
        List<TutorResponseDto> allMentors = adminService.findAllMentors();
        return new ListResponseDto<>(allMentors.size(), allMentors);
    }

    @GetMapping("/tutors/{tutorId}")
    public TutorResponseDto findTutor(@PathVariable Long tutorId) {
        return adminService.findTutorById(tutorId);
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

    @PutMapping("/tutors/{tutorId}/role")
    public ResponseEntity<Response> changeTutorRole(@PathVariable Long tutorId, @RequestBody TutorRoleRequestDto tutorRoleRequestDto) {
        adminService.changeTutorRole(tutorId, tutorRoleRequestDto.getRole());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "멘토 권한이 변경되었습니다."));
    }

    @PostMapping("/tutors/{tutorId}/schedule")
    public ResponseEntity<Response> addSchedule(
            @PathVariable Long tutorId, @RequestBody AdminScheduleRequestDto adminScheduleRequestDto,
            @AuthenticationPrincipal UserAdaptor userAdaptor) {
        adminService.addSchedule(userAdaptor.getMember(), tutorId, adminScheduleRequestDto);
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
