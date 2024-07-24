package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Application(Member member, String content) {
        this.member = member;
        this.content = content;
    }
}
