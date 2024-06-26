package com.dadingcoding.web.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Program extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "program_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int view = 0;

    private String photo;


}
