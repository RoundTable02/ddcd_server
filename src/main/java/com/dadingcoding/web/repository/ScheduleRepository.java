package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Schedule;
import com.dadingcoding.web.domain.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s where s.mentor.id = ?1 and s.scheduleType = ?2")
    List<Schedule> findAllByMentorIdAndScheduleType(Long id, ScheduleType scheduleType);

    @Query("select s from Schedule s where s.mentee.id = ?1 and s.scheduleType = ?2")
    List<Schedule> findAllByMenteeIdAndScheduleType(Long id, ScheduleType scheduleType);
}
