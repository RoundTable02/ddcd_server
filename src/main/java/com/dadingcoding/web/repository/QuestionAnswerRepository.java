package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QnaType;
import com.dadingcoding.web.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    List<QuestionAnswer> findAllByMemberIdAndQnaType(Long menteeId, QnaType qnaType);
}
