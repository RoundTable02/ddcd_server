package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.response.NoticeResponse;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.controller.dto.request.AddNoticeRequest;
import com.dadingcoding.web.controller.dto.request.UpdateNoticeRequest;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.NoticeService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("{notice_id}")
    public ResponseEntity<?> findNotice(@PathVariable long notice_id) {
        Notice notice = noticeService.findById(notice_id);

        // Notice 객체를 JSON 형태로 변환하여 반환
        Map<String, Object> noticeResponse = new HashMap<>();
        noticeResponse.put("id", notice.getId());
        noticeResponse.put("title", notice.getTitle());
        noticeResponse.put("content", notice.getContent());
        noticeResponse.put("datePosted", notice.getCreatedAt().toString());
        noticeResponse.put("visibility", notice.getVisibility());

        // 성공적인 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoticeResponse(200, noticeResponse));
    }

    @GetMapping
    public ResponseEntity<?> findNotices() {
        List<Notice> notices = noticeService.findAll();

        // 공지사항 목록을 JSON 형태로 변환하여 반환
        List<Map<String, Object>> noticeResponses = notices.stream().map(notice -> {
            Map<String, Object> noticeResponse = new HashMap<>();
            noticeResponse.put("notice_id", notice.getId());
            noticeResponse.put("title", notice.getTitle());
            noticeResponse.put("content", notice.getContent());
            noticeResponse.put("created_at", notice.getCreatedAt().toString());
            noticeResponse.put("updated_at", notice.getUpdatedAt().toString());

            // 작성자 정보 포함
            Map<String, Object> authorResponse = new HashMap<>();
            authorResponse.put("user_id", notice.getMember().getId());
            authorResponse.put("username", notice.getMember().getUsername());
            noticeResponse.put("author", authorResponse);

            // 가시성 정보 포함
            noticeResponse.put("visibility", notice.getVisibility());

            return noticeResponse;
        }).collect(Collectors.toList());

        // 성공적인 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoticeResponse(200, noticeResponses));
    }

    @PostMapping
    public ResponseEntity<?> addNotice(@RequestBody AddNoticeRequest request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();

        if (member.getRole() != Role.MANAGER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new Response(403, "권한이 없는 접근"));
        }

        noticeService.save(request, member);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(201, "공지가 성공적으로 등록되었습니다."));
    }

    @PutMapping("{notice_id}")
    public ResponseEntity<?> updateNotice(@PathVariable long notice_id, @RequestBody UpdateNoticeRequest request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();

        if (member.getRole() != Role.MANAGER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new Response(403, "권한이 없는 접근"));
        }

        Notice updatedNotice = noticeService.update(notice_id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "공지가 성공적으로 수정되었습니다."));

    }

    @DeleteMapping("{notice_id}")
    public ResponseEntity<?> deleteNotice(@PathVariable long notice_id, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        Member member = userAdaptor.getMember();

        if (member.getRole() != Role.MANAGER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new Response(403, "권한이 없는 접근"));
        }

        noticeService.delete(notice_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(200, "공지가 성공적으로 삭제되었습니다."));

    }
}
