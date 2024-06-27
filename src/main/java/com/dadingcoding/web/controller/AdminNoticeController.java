package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.AddNoticeRequest;
import com.dadingcoding.web.controller.dto.ChangeNoticeRequest;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminNoticeController {
    private final AdminService adminService;

    @PostMapping("/notice-register")
    public void addAdminNotice(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody AddNoticeRequest addNoticeRequest) {
        Member admin = userAdaptor.getMember();
        adminService.addNotice(addNoticeRequest.toEntity(admin));
        //TODO: return value required
    }

    @PutMapping("/notice-modify/{noticeId}")
    public void changeAdminNotice(@AuthenticationPrincipal UserAdaptor userAdaptor, @RequestBody ChangeNoticeRequest changeNoticeRequest, @PathVariable Long noticeId) {
        adminService.changeNotice(noticeId, changeNoticeRequest);
        //TODO: return value required
    }
}
