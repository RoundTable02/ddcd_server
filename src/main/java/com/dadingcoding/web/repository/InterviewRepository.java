package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long> {
    List<Interview> findAllByIntervieweeId(Long id);
}
