package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class MenteeService {

    private final ApplicationRepository applicationRepository;

    public void addApplication(Member member, AddApplicationRequestDto request) {
        Application application = request.toEntity(member);
        applicationRepository.save(application);
    }

}
