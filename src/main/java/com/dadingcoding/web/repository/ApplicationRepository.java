package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
