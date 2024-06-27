package com.dadingcoding.web.controller;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.controller.dto.AddNoticeRequest;
import com.dadingcoding.web.controller.dto.UpdateNoticeRequest;
import com.dadingcoding.web.domain.Role;
import com.dadingcoding.web.response.ExceptResponse;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.UserAdaptor;
import com.dadingcoding.web.service.NoticeService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// POST형식 "/users/notice_register" request의 header에서 bearer token 정보를 가져와 권한 확인,
// Notice 저장 후, 각 상황에 맞는 response 반환
// token을 받아서 어떤식으로 인증할지 결정해야함
//
//

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findNotice(@PathVariable long id) {
        try {
            Notice notice = noticeService.findById(id);

            // Notice 객체를 JSON 형태로 변환하여 반환
            Map<String, Object> noticeResponse = new HashMap<>();
            noticeResponse.put("id", notice.getId());
            noticeResponse.put("title", notice.getTitle());
            noticeResponse.put("content", notice.getContent());
            noticeResponse.put("datePosted", notice.getCreatedAt().toString());
            noticeResponse.put("visibility", notice.getVisibility());

            // 성공적인 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, noticeResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    @PostMapping("/notice_register")
    public ResponseEntity<?> addNotice(@RequestBody AddNoticeRequest request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        try {
            Member member = userAdaptor.getMember();

            if (member.getRole() != Role.MANAGER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new Response(403, "권한이 없는 접근"));
            }

            noticeService.save(request, member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(201, "공지가 성공적으로 등록되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류: " + e.getMessage(), false));
        }
    }

    @PutMapping("/notice-modify/{id}")   //id를 전달받아야 하는데, request에 id가 없다 -> 주소에 담아서 주거나 어떤 형태로든 받아야 함
    public ResponseEntity<?> updateNotice(@PathVariable long id, @RequestBody UpdateNoticeRequest request, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        try {
            Member member = userAdaptor.getMember();

            if (member.getRole() != Role.MANAGER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new Response(403, "권한이 없는 접근"));
            }

            Notice updatedNotice = noticeService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "공지가 성공적으로 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    @DeleteMapping("/notice-delete/{id}")   //id를 전달받아야 하는데, request에 id가 없다 -> 주소에 담아서 주거나 어떤 형태로든 받아야 함
    public ResponseEntity<?> updateNotice(@PathVariable long id, @AuthenticationPrincipal UserAdaptor userAdaptor) {
        try {
            Member member = userAdaptor.getMember();

            if (member.getRole() != Role.MANAGER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new Response(403, "권한이 없는 접근"));
            }

            noticeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, "공지가 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }
}
