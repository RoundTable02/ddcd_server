package com.dadingcoding.web.security;

import com.dadingcoding.web.domain.Member;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

@Getter
@Slf4j
public class UserAdaptor extends User {
    private Member member;

    public UserAdaptor(Member member) {
        super(member.getUsername(), member.getPassword(), member.getAuthorities());
        this.member = member;
    }
}
