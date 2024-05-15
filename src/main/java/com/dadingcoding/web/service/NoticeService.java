package com.dadingcoding.web.service;

import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.dto.AddNoticeRequest;
import com.dadingcoding.web.dto.UpdateNoticeRequest;
import com.dadingcoding.web.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice save(AddNoticeRequest request) {
        return noticeRepository.save(request.toEntity());
    }

//    public void delete(long id) {
//        noticeRepository.deleteById(id);
//    }
//
    @Transactional
    public Notice update(long id, UpdateNoticeRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        notice.update(request.getTitle(), request.getContent());

        return notice;
    }

    public Notice findById(long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
