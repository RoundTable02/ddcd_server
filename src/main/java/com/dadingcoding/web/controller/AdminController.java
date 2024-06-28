package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.request.AdminScheduleRequestDto;
import com.dadingcoding.web.controller.dto.response.ListResponseDto;
import com.dadingcoding.web.controller.dto.request.TutorRoleRequestDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ListResponseDto<Member> findAllTutors() {
        List<Member> mentors = adminService.findAllMentors();
        return new ListResponseDto<>(mentors.size(), mentors);
    }

    @GetMapping("/tutors/{tutorId}")
    public Member findTutor(@PathVariable Long tutorId) {
        return adminService.findTutorById(tutorId);
    }

    @GetMapping("/students")
    public ListResponseDto<Member> findAllStudents() {
        List<Member> students = adminService.findAllStudents();
        return new ListResponseDto<>(students.size(), students);
    }

    @GetMapping("/students/{studentId}")
    public Member findStudent(@PathVariable Long studentId) {
        return adminService.findStudentById(studentId);
    }

    @PutMapping("/tutors/{tutorId}")
    public void changeTutorRole(@PathVariable Long tutorId, @RequestBody TutorRoleRequestDto tutorRoleRequestDto) {
        adminService.changeTutorRole(tutorId, tutorRoleRequestDto.getRole());
        // TODO : Response 추가 필요
    }

    @PostMapping("/tutors/{tutorId}/schedule")
    public void addSchedule(
            @PathVariable Long tutorId, @RequestBody AdminScheduleRequestDto adminScheduleRequestDto,
            @AuthenticationPrincipal UserAdaptor userAdaptor) {
        adminService.addSchedule(userAdaptor.getMember(), tutorId, adminScheduleRequestDto);
        // TODO : Response 추가 필요
    }

    @GetMapping("/reports")
    public ListResponseDto<Report> findAllReports() {
        List<Report> reports = adminService.findAllReports();
        return new ListResponseDto<>(reports.size(), reports);
    }


}
