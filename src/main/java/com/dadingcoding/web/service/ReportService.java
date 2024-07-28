package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.request.AddQuestionRequestDto;
import com.dadingcoding.web.controller.dto.request.ReportRequestDto;
import com.dadingcoding.web.controller.dto.response.AnswerDto;
import com.dadingcoding.web.controller.dto.response.QuestionDto;
import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QuestionAnswer;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.exception.ErrorCode;
import com.dadingcoding.web.exception.NoAuthorityToAccessException;
import com.dadingcoding.web.repository.ApplicationRepository;
import com.dadingcoding.web.repository.QuestionAnswerRepository;
import com.dadingcoding.web.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    public void createReport(Member member, ReportRequestDto requestDto) {
        Report report = Report.builder()
                .member(member)
                .content(requestDto.getContent())
                .title(requestDto.getTitle())
                .build();

        reportRepository.save(report);
    }

    public void updateReport(Long reportId, Member member, ReportRequestDto requestDto) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 보고서가 없습니다."));

        if (report.getMember().getId() != member.getId()) {
            throw new NoAuthorityToAccessException(ErrorCode.NO_AUTHORITY_TO_ACCESS);
        }

        report.updateReport(report.getTitle(), report.getContent());
    }
}
