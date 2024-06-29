package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.ProgramMember;
import com.dadingcoding.web.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramMemberRepository extends JpaRepository<ProgramMember, Long> {
}
