package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", updatable = false)
    private Long id;

    @Column(name = "visibility")
    private String visibility;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Notice(String title, String content, String visibility, Member member) {
        this.setTitle(title);
        this.setContent(content);
        this.visibility = visibility;
        this.member = member;
    }

    public void update(String title, String content, String visibility) {
        this.setTitle(title);
        this.setContent(content);
        this.visibility = visibility;
    }
}
