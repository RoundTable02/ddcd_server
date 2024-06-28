package com.dadingcoding.web.service;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.controller.dto.request.AddNoticeRequest;
import com.dadingcoding.web.controller.dto.request.UpdateNoticeRequest;
import com.dadingcoding.web.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void save(AddNoticeRequest request, Member member) {
        Notice notice = request.toEntity(member);
        noticeRepository.save(notice);
    }

    @Transactional
    public void delete(long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        noticeRepository.deleteById(id);
    }

    @Transactional
    public Notice update(long id, UpdateNoticeRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        notice.update(request.getTitle(), request.getContent(), request.getVisibility());

        return notice;
    }

    public Notice findById(long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
