package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.QnA.Question;
import com.dadingcoding.web.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    List<QuestionAnswer> findAllByMemberId(Long id);

    List<QuestionAnswer> findByQuestionId(Long questionId);
}
