package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", updatable = false)
    private Long id;

    @Column(name = "visibility")
    private String visibility;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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
