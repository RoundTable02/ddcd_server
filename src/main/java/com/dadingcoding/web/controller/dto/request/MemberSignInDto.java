package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Gender;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class MemberSignInDto {
    @Schema(description = "이메일", nullable = false, example = "example123@gmail.com")
    private String email;

    @Schema(description = "비밀번호", nullable = false, example = "qwe123")
    private String password;

    @Schema(description = "이름", nullable = false, example = "가나다")
    private String name;

    @Schema(description = "학교명", nullable = false, example = "동북고등학교")
    private String school;

    @Schema(description = "핸드폰 번호", nullable = false, example = "010-0000-0000")
    private String phone;

    @Schema(description = "나이", nullable = false, example = "15")
    private int age;

    @Schema(description = "성별 (MALE/FEMALE)", nullable = false, example = "FEMALE")
    private Gender gender;

    @Schema(description = "역할 (MANAGER, PREMENTOR, MENTOR, MENTEE)", nullable = false, example = "MENTEE")
    private Role status;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .username(name)
                .school(school)
                .phone(phone)
                .age(age)
                .gender(gender)
                .role(status)
                .build();
    }
}

