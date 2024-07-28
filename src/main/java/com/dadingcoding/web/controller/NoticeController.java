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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "공지 컨트롤러", description = "멘토 관련 로직을 담당하는 컨트롤러입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "개별 공지 조회", description = "공지 게시글 정보 GET")
    @GetMapping("/{notice_id}")
    public ResponseEntity<?> findNotice(@PathVariable long notice_id) {
        Notice notice = noticeService.findById(notice_id);

        Map<String, Object> noticeResponse = new HashMap<>();
        noticeResponse.put("id", notice.getId());
        noticeResponse.put("title", notice.getTitle());
        noticeResponse.put("content", notice.getContent());
        noticeResponse.put("datePosted", notice.getCreatedAt().toString());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoticeResponse(200, noticeResponse));
    }

    @Operation(summary = "공지글 전체 조회", description = "게시글 전체 정보 GET")
    @GetMapping
    public ResponseEntity<?> findNotices() {
        List<Notice> notices = noticeService.findAll();

        List<Map<String, Object>> noticeResponses = notices.stream().map(notice -> {
            Map<String, Object> noticeResponse = new HashMap<>();
            noticeResponse.put("notice_id", notice.getId());
            noticeResponse.put("title", notice.getTitle());
            noticeResponse.put("content", notice.getContent());
            noticeResponse.put("created_at", notice.getCreatedAt().toString());
            noticeResponse.put("updated_at", notice.getUpdatedAt().toString());

            Map<String, Object> authorResponse = new HashMap<>();
            authorResponse.put("user_id", notice.getMember().getId());
            authorResponse.put("username", notice.getMember().getUsername());
            noticeResponse.put("author", authorResponse);

            return noticeResponse;
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoticeResponse(200, noticeResponses));
    }

    @Operation(summary = "공지 추가", description = "MANAGER 권한의 멤버가 공지 추가")
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

    @Operation(summary = "공지 수정", description = "MANAGER 권한의 멤버가 공지 수정")
    @PutMapping("/{notice_id}")
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

    @Operation(summary = "공지 삭제", description = "MANAGER 권한의 멤버가 공지 삭제")
    @DeleteMapping("/{notice_id}")
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
