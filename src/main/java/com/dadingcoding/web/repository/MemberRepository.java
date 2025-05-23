package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    List<Member> findAllByRole(Role role);
}
