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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    public void updateReport(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
