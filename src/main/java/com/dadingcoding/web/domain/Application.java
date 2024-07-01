package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class Application extends PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
