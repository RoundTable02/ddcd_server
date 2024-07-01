package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.ProgramMember;
import com.dadingcoding.web.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramMemberRepository extends JpaRepository<ProgramMember, Long> {
    @Modifying
    @Query("delete from ProgramMember p where p.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);
}
