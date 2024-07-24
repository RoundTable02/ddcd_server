package com.dadingcoding.web.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content", nullable = false)
    private String content;
}
