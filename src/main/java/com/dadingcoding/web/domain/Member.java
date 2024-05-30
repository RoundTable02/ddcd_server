package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity @Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; //ADMIN, MANAGER, USER

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    private String refreshToken;

    private LocalDateTime createDate;

    @Builder
    public Member(String username, String password, String email, Role role, LocalDateTime createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createDate = createDate;
    }

}
