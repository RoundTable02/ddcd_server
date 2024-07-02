package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.QnA.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByMemberId(Long Id);
}
