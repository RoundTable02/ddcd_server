package com.dadingcoding.web.controller;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.controller.dto.AddNoticeRequest;
import com.dadingcoding.web.controller.dto.UpdateNoticeRequest;
import com.dadingcoding.web.response.ExceptResponse;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.service.NoticeService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// POST형식 "/users/notice_register" request의 header에서 bearer token 정보를 가져와 권한 확인,
// Notice 저장 후, 각 상황에 맞는 response 반환
// token을 받아서 어떤식으로 인증할지 결정해야함
//
//

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/notice_register")
    public ResponseEntity<?> addNotice(@RequestBody AddNoticeRequest request, @AuthenticationPrincipal Member member) {
        try {
            noticeService.save(request, member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(201, "공지가 성공적으로 등록되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    @PutMapping("/notice-modify/{id}")   //id를 전달받아야 하는데, request에 id가 없다 -> 주소에 담아서 주거나 어떤 형태로든 받아야 함
    public ResponseEntity<?> updateNotice(@PathVariable long id, @RequestBody UpdateNoticeRequest request, @AuthenticationPrincipal Member member) {
        try {
            Notice updatedNotice = noticeService.update(id, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(201, "공지가 성공적으로 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }

    //꼭 visibility로 받아야 하는지 의문. token에서 무슨 권한을 가지는지 보면 token 하나로 조회, 등록, 수정 한 번에 가능하지 않나?
//    @GetMapping("/notice/{id}")
//    public ResponseEntity<?> findNotice(@PathVariable long id) {
//        try {
//            Notice notice = noticeService.findById(id);
//            if(member.getRole() != notice.getMember().getRole()) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(403, "권한이 없는 접근"));
//            }
//
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new Response(201, "공지가 성공적으로 등록되었습니다."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptResponse(500, "서버 내부 오류", false));
//        }
//    }
}
