package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    private String school;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role; //MANAGER, PREMENTOR, MENTOR, MENTEE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Member mentor;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> mentees = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mentee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    private String refreshToken;

    private LocalDateTime createDate;
}
