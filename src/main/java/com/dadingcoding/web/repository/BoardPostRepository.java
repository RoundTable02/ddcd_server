package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPostRepository extends JpaRepository<BoardPost,Long> {
}
