package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByMemberId(Long id);

    Optional<Application> findByMemberId(Long memberId);
}
