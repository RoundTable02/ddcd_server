package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
