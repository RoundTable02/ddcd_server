package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.request.AddQuestionRequestDto;
import com.dadingcoding.web.controller.dto.response.AnswerDto;
import com.dadingcoding.web.controller.dto.response.QuestionDto;
import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QuestionAnswer;
import com.dadingcoding.web.repository.ApplicationRepository;
import com.dadingcoding.web.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
@Transactional
public class MenteeService {

    private final ApplicationRepository applicationRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public void addApplication(Member member, AddApplicationRequestDto request) {
        Application application = request.toEntity(member);
        applicationRepository.save(application);
    }

    public void addQuestion(Member member, AddQuestionRequestDto request) {
        QuestionAnswer question = request.toEntity(member);
        questionAnswerRepository.save(question);
    }

    public List<QuestionDto> findAllQuestions(Long id) {
        List<QuestionAnswer> questions = questionAnswerRepository.findAllByMemberId(id);

        return questions.stream()
                .map(QuestionDto::toDto)
                .collect(Collectors.toList());
    }

    public QuestionAnswer findQuestionById(Long id) {
        return questionAnswerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 질문이 없습니다."));
    }

    public List<AnswerDto> findAllAnswers(Long questionId) {
        List<QuestionAnswer> answers = questionAnswerRepository.findByQuestionId(questionId);

        return answers.stream()
                .map(AnswerDto::toDto)
                .collect(Collectors.toList());
    }
}
