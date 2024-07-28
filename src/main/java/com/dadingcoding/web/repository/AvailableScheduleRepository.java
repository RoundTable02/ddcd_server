package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.AvailableSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableScheduleRepository extends JpaRepository<AvailableSchedule,Long> {
}
