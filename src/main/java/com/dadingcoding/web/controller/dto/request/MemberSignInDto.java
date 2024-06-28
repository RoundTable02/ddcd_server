package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Gender;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class MemberSignInDto {
    private String email;
    private String password;
    private String name;
    private String school;
    private int age;
    private Gender gender;
    private Role status;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .username(name)
                .school(school)
                .age(age)
                .gender(gender)
                .role(status)
                .build();
    }
}

